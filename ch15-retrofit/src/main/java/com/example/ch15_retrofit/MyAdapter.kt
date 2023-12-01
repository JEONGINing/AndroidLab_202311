package com.example.ch15_retrofit

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch15_retrofit.databinding.ItemMainBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val application: MyApplication, val datas: MutableList<User>?):
        RecyclerView.Adapter<MyViewHolder>() {
    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = datas?.get(position)
        holder.binding.id.text = user?.id
        holder.binding.firstNameView.text = user?.firstName
        holder.binding.lastNameView.text = user?.lastName

        user?.avatar?.let { // 서버 정보에 이미지 url이 있다면...
            // 네트워킹...
            val call = application.networkService.getAvatarImage(it)
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                    holder.binding.avatarView.setImageBitmap(bitmap)
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}