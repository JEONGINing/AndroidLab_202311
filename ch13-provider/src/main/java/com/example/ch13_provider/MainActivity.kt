package com.example.ch13_provider

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.ch13_provider.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    // 우리가 만드는 파일 경로. 카메라 앱에게 공유할 파일
    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val galleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult() // Intent 발생 역할
        ) {
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            // gallery에서 넘어온 결과는 gallery provider 이용 uri, 그 uri에 유저가 선택한 파일 정보 포함.
            // gallery provider는 파일을 읽는 stream 제공한다. 물론 file 경로를 받아 직접 처리할 수도 있다.
            val stream = contentResolver.openInputStream(it.data!!.data!!)
            val bitmap = BitmapFactory.decodeStream(stream, null, option)
            stream?.close()
            // 화면 출력
            binding.userImageView.setImageBitmap(bitmap)
        }

        binding.galleryButton.setOnClickListener {
            // gallery 목록 activity..
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        val cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val option = BitmapFactory.Options()
            option.inSampleSize = 10
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            // 화면 출력
            binding.userImageView.setImageBitmap(bitmap)
        }

        binding.cameraButton.setOnClickListener {
            // 파일 준비
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES) // 외장 앱별, 사진 저장되는 곳
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                dir
            )
            filePath = file.absolutePath
            // 카메라 앱에게 넘길 파일 정보.. url 형식으로, uri 객체로.
            val uri = FileProvider.getUriForFile(this, "com.example.ch13_provider.fileprovider", file)
            // 카메라 앱 실행
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            cameraLauncher.launch(intent)
        }
    }
}