package com.example.ch12_service

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch12_service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            // 개발자가 주는 식별자. 나중에 구동 중인 jobservice를 이 식별자로 외부에서 종료시킬 수 있다.
            // 정보에 우리의 서비스 이름이 들어간다.
            val builder = JobInfo.Builder(1, ComponentName(this, MyService::class.java))
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // wifi enable 상황...
            // 등록
            scheduler!!.schedule(builder.build())
        }
    }
}