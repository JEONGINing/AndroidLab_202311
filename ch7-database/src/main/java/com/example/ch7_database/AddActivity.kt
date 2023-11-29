package com.example.ch7_database

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_database.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val inputData = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            // 배열을 []로 선언하지 않는다. 물론 이용 시에는 [0]으로 사용 가능.
            db.execSQL("insert into TODO_DB (todo) values (?)", arrayOf(inputData))
            db.close()
            // 화면을 MainActivity로 되돌린다. 결과값 포함시켜서.
            intent.putExtra("result",inputData)
            setResult(Activity.RESULT_OK, intent)
            // 자신을 종료시켜서 시스템에 의해 자동으로 이전화면으로 되돌아가게
            finish()
        }
    }
}