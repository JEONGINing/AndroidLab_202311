package com.example.ch7_database

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    // db select data를 담을 List
    val datas: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // db select
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TODO_DB", null)
        // obj.run{}, obj.apply{}, obj.with{}, obj.let{}
        // run, apply 등의 함수를 호출하면서 매개변수로 함수 하나 지정
        // 매개변수의 함수를 실행시켜주는 함수
        // obj.run{}으로 하면, 이 obj 객체의 함수인 것처럼 효과를 내줘서 {} 안에서 obj 멤버 접근이 용이.
        cursor.run {  // 람다 함수
            while (moveToNext()) {
                datas.add(getString(1))
            }
        }
        db.close()

        var result = ""
        // 람다함수 중에서 매개변수 하나 짜리는 매개변수를 선언하지 않고, 람다함수 내에서 it 예약어로 대체 가능하다.
        datas.forEach {
            result += "$it \n"
        }
        binding.mainTextView.text = result

        //AddActivity 실행 준비. 되돌아 왔을 때 콜백 명시
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult() // 첫번째 매개변수. 요청 실행자. 화면 전환시켜준다.
        ) { // 두번째 매개변수. 콜백.
            //add에서 넘긴 데이터 받고
            // !! : null이면 exception 발생시켜 달라는 요청
            it.data!!.getStringExtra("result")?.let {
                datas.add(it)

                var result = ""
                datas.forEach {
                    result += "$it \n"
                }
                binding.mainTextView.text = result
            }
        }

        binding.mainButton.setOnClickListener{
            // 화면 전환
            val intent = Intent(this, AddActivity::class.java)
            launcher.launch(intent)
        }
    }
}