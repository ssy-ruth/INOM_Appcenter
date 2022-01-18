package com.example.inomtest

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams){

    override fun doWork() : ListenableWorker.Result {
        Log.d(TAG, "Performing long running task in scheduled job")

        //여기에 장기실행작업을 추가.

        return ListenableWorker.Result.success()
    }

    companion object {
        private val TAG = "MyWorker"
    }
}