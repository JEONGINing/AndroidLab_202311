package com.example.ch15_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch15_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val userList = mutableListOf<User>() // 서버에서 받은 데이터
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyAdapter(applicationContext as MyApplication, userList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val networkService = (applicationContext as MyApplication).networkService
        val call = networkService.getUserList("1")
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                // 서버 데이터 포함시켜서 화면 갱신
                // !!은 null safety operator. null이면 exception 발생시켜라.
                userList.addAll(response.body()!!.data!!)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}