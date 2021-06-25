package com.example.learn_demo.lock

import android.util.Log
import java.util.concurrent.CountDownLatch
import kotlin.random.Random

/**
 * CountDownLatch概念:
 *    CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
 *    CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。
 * 使用一个计数器进行实现。计数器初始值为线程的数量。当每一个线程完成自己任务后，
 * 计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成一些任务，
 * 然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。
 *
 * CountDownLatch典型用法：
 *     1.某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，
 * 每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，
 * 在CountDownLatch上await()的线程就会被唤醒。
 *
 *     2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
 * 类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，
 * 将其计算器初始化为1，多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，
 * 计数器变为0，多个线程同时被唤醒。
 *
 * CountDownLatch的不足:
 *    CountDownLatch是一次性的，计算器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，
 * 当CountDownLatch使用完毕后，它不能再次被使用。
 */
class CountDownLatchTest {
    private val lock = CountDownLatch(3)

    /**
     * 线程等待子线程执行完成在执行
     */
    fun demo0() {
        Thread(getRunnable(), "ThreadA").start()
        Thread(getRunnable(), "ThreadB").start()
        Thread(getRunnable(), "ThreadC").start()

        try {
            Log.d("vian", "main thread wait for subthread finishing.")
            lock.await()
            Log.d("vian", "main thread start executing...")
        } catch (e: InterruptedException) {
            Log.e("vian", e.message)
        }
    }

    private fun getRunnable(): Runnable = Runnable {
        Log.d("vian", "${Thread.currentThread().name} start...")
        Thread.sleep(Random.nextInt(1, 5) * 1000L)
        lock.countDown()
        Log.d("vian", "${Thread.currentThread().name} end...")
    }

    /**
     * 百米赛跑，4名运动员选手到达场地等待裁判口令，裁判一声口令，选手听到后同时起跑，当所有选手到达终点，裁判进行汇总排名
     */
    private val startLock = CountDownLatch(1)
    private val endLock = CountDownLatch(4)
    fun demo1() {
        Thread(getRunnerRunnable(), "RunnerA").start()
        Thread(getRunnerRunnable(), "RunnerB").start()
        Thread(getRunnerRunnable(), "RunnerC").start()
        Thread(getRunnerRunnable(), "RunnerD").start()

        try {
            startLock.countDown()
            Log.d("vian", "start running")

            endLock.await()
            Log.d("vian", "end running")
        } catch (e: InterruptedException) {
            Log.e("vian", e.message)
        }
    }

    private fun getRunnerRunnable(): Runnable = Runnable {
        Log.d("vian", "${Thread.currentThread().name} start...")
        startLock.await()
        Thread.sleep(Random.nextInt(1, 5) * 1000L)
        endLock.countDown()
        Log.d("vian", "${Thread.currentThread().name} end...")
    }


}