package com.example.ch11_receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.ch11_receiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 퍼미션 다이얼로그, 사후 처리 launcher
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            if(it.all {p -> p.value == true}) {
                noti()
            } else {
                Toast.makeText(this,"permission denied....", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {
            //알림이 퍼미션이 요구되는 것은 33버전부터.. 이전 버전에는 없다. 없는 것을 체크하면 denied로 나온다.
            //Build.VERSION.SDK_INT - 유저 폰 api level
            if(Build.VERSION.SDK_INT >= 33) {
                // 퍼미션 체크
                if(ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == PackageManager.PERMISSION_GRANTED) {
                    noti()
                } else {
                    launcher.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            } else {
                noti()
            }
        }
    }

    fun noti() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= 26) {
            //channel 준비..
            val channel = NotificationChannel("one","one channel", NotificationManager.IMPORTANCE_LOW)
            // 준비된 채널을 시스템에 등록
            manager.createNotificationChannel(channel)
            // 시스템에 등록된 채널을 적용해 빌더를 만든다.
            builder = NotificationCompat.Builder(this, "one")
        } else {
            builder = NotificationCompat.Builder(this)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentText("안녕하세요")
        builder.setContentTitle("메시지 도착")

        // 터치 이벤트 의뢰..
        val intent = Intent(this, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 10, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent)
        // 알림 발생...
        manager.notify(11, builder.build())
    }
}