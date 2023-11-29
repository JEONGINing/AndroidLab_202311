package com.example.ch3_view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ch3_view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 코틀린은 ;을 강제하지 않는다.
    // null safety를 지원하는 언어라 타입으로 nullable, non-null을 구분해서 사용한다.
    // 멤버변수 선언과 동시에 초기값을 줄 수 없다면, 자동 초기화를 지원하지 않음으로 nullable로 선언해서 null로 초기화
    // 멤버변수 선언과 동시에 초기값을 줄 수는 없지만, 그렇다고 이 객체는 절대 null이 대입되지 않는다면.. lateinit 예약어로 초기화 시점을 미룬다.
//    lateinit var buttonView: Button
//    lateinit var editView: EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //화면출력: inflate. 뷰 객체 생성 완료.
//        setContentView(R.layout.activity_main)
//        //필요 뷰 객체 획득
//        buttonView = findViewById(R.id.buttonView)
//        editView = findViewById(R.id.editView)
//
//        //뷰에 이벤트 등록
//        buttonView.setOnClickListener {
//            //유저 입력 데이터 획득.
//            var data = editView.text.toString()
//            Log.d("kkang", data);
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ViewBinding에 infalte 의뢰
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonView.setOnClickListener {
            val data = binding.editView.text.toString()
            Log.d("kkang", data)
        }
    }
}