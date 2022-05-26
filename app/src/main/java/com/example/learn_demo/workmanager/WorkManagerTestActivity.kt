package com.example.learn_demo.workmanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_workmanager.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2021/7/9 5:10 下午
 */
class WorkManagerTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "WorkManagerTestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager)

        btn_start_one_time_work.setOnClickListener {
            startOneTimeWork()
        }

        btn_stop_one_time_work.setOnClickListener {
            stopOneTimeWork()
        }

        btn_start_period_work.setOnClickListener {
            startPeriodWork()
        }

        btn_stop_period_work.setOnClickListener {
            stopPeriodWork()
        }
    }

    private fun startOneTimeWork() {
        val workId = Random.nextInt(0, 100)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED) //连接到wifi
            .setRequiresCharging(true)//正在充电
            .build()

        val workRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(constraints) //设置约束
                .setInputData(
                    workDataOf(
                        "id" to "one_time_work[$workId]"
                    )
                ) //设置初始参数
                .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "one_time_work",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        Log.d(TAG, "create a new one time work. id=one_time_work[$workId]")
    }

    private fun stopOneTimeWork(){
        WorkManager.getInstance(this).cancelUniqueWork("one_time_work")
    }

    private fun startPeriodWork() {
        val workId = Random.nextInt(100, 200)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED) //连接到wifi
            .setRequiresCharging(true)//正在充电
            .build()

        val workRequest = PeriodicWorkRequestBuilder<UploadWorker>(
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS, // repeatInterval 最小值 PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS // 15 minutes.
            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS - 10000, TimeUnit.MILLISECONDS  // flexTimeInterval 最小值 PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS // 5 minutes.
        ) // flexInterval
            .setConstraints(constraints) //设置约束
            .setInputData(
                workDataOf(
                    "id" to "one_time_work[$workId]"
                )
            ) //设置初始参数
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "period_work",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
        Log.d(TAG, "create a new period work. id=period_work[$workId]")
    }

    private fun stopPeriodWork(){
        WorkManager.getInstance(this).cancelUniqueWork("period_work")
    }

    class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
        Worker(appContext, workerParams) {
        override fun doWork(): Result {
            val id =
                inputData.getString("id") ?: return Result.failure()
            val threadName = Thread.currentThread().name

            Log.d(TAG, "start work[$id] on thread[$threadName]")

            // Do the work here--in this case, upload the images.
            Thread.sleep(10000)

            Log.d(TAG, "end work[$id] on thread[$threadName]")

            // Indicate whether the work finished successfully with the Result
            return Result.success()
        }
    }
}



