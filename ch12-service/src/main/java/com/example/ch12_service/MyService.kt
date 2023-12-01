package com.example.ch12_service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("kkang", "onStartJob...............")
        return false // 업무 종료 의미
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("kkang", "onStopJob................")
        return true
    }
}