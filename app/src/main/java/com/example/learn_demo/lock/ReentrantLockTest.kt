package com.example.learn_demo.lock

import android.util.Log
import java.util.*
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2021/3/12 3:42 PM
 *
 * 1. 可重入性
 * 2. 公平性测试
 * 3. 支持中断响应
 */
class ReentrantLockTest {

    private val reentrantLock = ReentrantLock(true)

    fun test(){
        testCondition2()
    }

    //====================================== 重入性测试 =========================================//

    /**
     *
     * ReentrantLock是可重入锁，所以一个线程可以反复得到相同的一把锁，它有一个与锁相关的获取计数器，
     * 如果拥有锁的某个线程再次得到锁，那么获取计数器就加1，然后锁需要被释放两次才能获得真正释放(重入锁)。
     *
     * 输出：
     *    lock success. thread = Thread-2
     *    lock success. thread = Thread-2
     *    unlock success. thread = Thread-2
     *    unlock success. thread = Thread-2
     *    lock success. thread = Thread-3
     *    lock success. thread = Thread-3
     *    unlock success. thread = Thread-3
     *    unlock success. thread = Thread-3
     *
     */
    fun testReentrant() {
        Thread(reentrantRunnable).start()
        Thread(reentrantRunnable).start()
    }

    private val reentrantRunnable = Runnable {
        for (i in 1..3) {
            reentrantLock.lock()
            Log.d("vian", "lock success. thread = ${Thread.currentThread().name}")
        }

        for (i in 1..3) {
            reentrantLock.unlock()
            Log.d("vian", "unlock success. thread = ${Thread.currentThread().name}")
        }
    }


    //====================================== 公平性测试 =========================================//

    /**
     * val reentrantLock = ReentrantLock()
     * 非公平情况下输出：
     *      lock success. thread = Thread-2
     *      lock success. thread = Thread-3
     *      lock success. thread = Thread-5
     *      lock success. thread = Thread-4
     *      lock success. thread = Thread-6
     *
     * val reentrantLock = ReentrantLock(true)
     * 公平情况下输出：
     *      lock success. thread = Thread-2
     *      lock success. thread = Thread-3
     *      lock success. thread = Thread-4
     *      lock success. thread = Thread-5
     *      lock success. thread = Thread-6
     */
    fun testFair() {
        for (i in 1..5) {
            Thread(fairRunnable).start()
        }
    }

    private val fairRunnable = Runnable {
        reentrantLock.lock()
        Log.d("vian", "lock success. thread = ${Thread.currentThread().name}")
        reentrantLock.unlock()
    }




    //====================================== 中断响应 =========================================//

    /**
     * 处于阻塞的线程，即在执行Object对象的wait()、wait(long)、wait(long, int)，
     * 或者线程类的join()、join(long)、join(long, int)、sleep(long)、sleep(long,int)方法后线程的状态，
     * 当线程调用interrupt()方法后，这些方法将抛出InterruptedException异常，
     * 并清空线程的中断状态，即isInterrupted()返回false
     *
     * 如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、1.5中的condition.await、以及可中断的通道上的 I/O 操作方法后可进入阻塞状态），
     * 则线程会一直检查中断状态标示，如果发现中断状态标示为true，则会在这些阻塞方法（sleep、join、wait、1.5中的condition.await及可中断的通道上的 I/O 操作方法）
     * 调用处抛出InterruptedException异常，并且在抛出异常后立即将线程的中断标示位清除，即重新设置为false。
     */
    fun testInterrupt() {
        val lock1 = ReentrantLock()
        val lock2 = ReentrantLock()
        val thread1 = Thread(InterruptRunnable(lock1, lock2)).also {
            it.start()
        }
        val thread2 = Thread(InterruptRunnable(lock2, lock1)).also {
            it.start()
        }
        Thread.sleep(2000)
        thread1.interrupt()
    }

    class InterruptRunnable(val lock1: Lock, val lock2: Lock) : Runnable {
        override fun run() {
            try {
                lock1.lockInterruptibly()
                Log.d(
                    "vian",
                    "lock success. thread = ${Thread.currentThread().name}, lock = lock[${lock1.hashCode()}]"
                )

                Thread.sleep(500)

                lock2.lockInterruptibly()
                Log.d(
                    "vian",
                    "lock success. thread = ${Thread.currentThread().name}, lock = lock[${lock2.hashCode()}]"
                )

            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                try {
                    lock1.unlock()
                    lock2.unlock()
                } catch (e: IllegalMonitorStateException) {
                    e.printStackTrace()
                }
            }
        }
    }

    //====================================== Condition的简单demo =========================================//
    private val condition1 = reentrantLock.newCondition()

    fun testCondition() {
        Thread(awaitRunnable).start()
        Thread(notifyRunnable).start()
    }

    private val awaitRunnable = Runnable {
        reentrantLock.lock()
        Log.d("vian", "lock success. thread = ${Thread.currentThread().name}")
        condition1.await()
        Log.d("vian", "await success. thread = ${Thread.currentThread().name}")
        reentrantLock.unlock()
    }

    private val notifyRunnable = Runnable {
        reentrantLock.lock()
        Log.d("vian", "lock success. thread = ${Thread.currentThread().name}")
        condition1.signal()
        Log.d("vian", "signal success. thread = ${Thread.currentThread().name}")
        reentrantLock.unlock()
    }






    //====================================== Condition demo - producer and consumer =========================================//
    private val notFull = reentrantLock.newCondition()
    private val notEmpty = reentrantLock.newCondition()
    private val list = LinkedList<Int>()
    private val max = 3

    fun testCondition2() {
        Thread(producerRunnable).start()
        Thread(consumeRunnable).start()
    }

    private val producerRunnable = Runnable {
        for (i in 1..100) {
            reentrantLock.lock()
            if (list.size == max) {
                Log.d("vian", "full, wait consumer to consume.")
                notFull.await()
                Log.d("vian", "not full, start to produce.")
            }

            list.add(Random.nextInt())
            Log.d("vian", "produce one.")

            if (list.size == 1) {
                notEmpty.signal()
            }
            reentrantLock.unlock()
        }
    }

    private val consumeRunnable = Runnable {
        for (i in 1..100) {
            reentrantLock.lock()
            if (list.size == 0) {
                Log.d("vian", "empty, wait producer to produce.")
                notEmpty.await()
                Log.d("vian", "not empty, start to consume.")
            }

            list.poll()
            Log.d("vian", "consume one.")

            if (list.size == max - 1) {
                notFull.signal()
            }
            reentrantLock.unlock()
        }
    }

}