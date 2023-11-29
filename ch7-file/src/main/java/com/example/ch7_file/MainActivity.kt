package com.example.ch7_file

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_file.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 내장 메모리
        binding.button1.setOnClickListener {
            val file = File(filesDir, "test.txt")
            val writeStream = file.writer()
            writeStream.write("hello world - internal")
            writeStream.flush()

            val readStream = file.reader().buffered()
            readStream.forEachLine {
                binding.textView.text = it
            }
        }

        // 외장(앱별) 메모리
        binding.button2.setOnClickListener {
            val file = File(getExternalFilesDir(null), "test.txt")
            val writeStream = file.writer()
            writeStream.write("hello world - external")
            writeStream.flush()

            val readStream = file.reader().buffered()
            readStream.forEachLine {
                binding.textView.text = it
            }
        }
    }
}