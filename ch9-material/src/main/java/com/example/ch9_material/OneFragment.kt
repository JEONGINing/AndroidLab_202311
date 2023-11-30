package com.example.ch9_material

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch9_material.databinding.FragmentOneBinding
import com.example.ch9_material.databinding.ItemRecyclerviewBinding

// 항목 구성 뷰를 가지는 역할
// 주 생성자에 한해서.. 생성자 매개변수에 val, var을 추가하면 그 자체가 클래스 멤버 변수가 된다.
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

// 항목 구성 역할
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<MyViewHolder>() {
    // 항목 갯수 판단, 자동호출
    override fun getItemCount(): Int {
        return datas.size
    }

    // 스크롤 되어 항목이 100개가 나왔다고 100번 호출되지 않는다. 적절하게 재사용 해준다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    // 각 항목을 구성하기 위해서 호출, 데이터 출력, 이벤트 등록 등..
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.itemData.text = datas[position]
    }
}

class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

    // 항목이 출력된 후 마지막 한번 호출
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        // 뷰(recyclerview)의 사이즈 획득
        val width = parent.width
        val height = parent.height

        // 드로잉 하고자 하는 이미지 사이즈 획득
        val drawable: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val drWidth = drawable?.intrinsicWidth
        val drHeight = drawable?.intrinsicHeight
        // 이미지 출력되는 left/top의 좌표 계산
        val left = width/2 - drWidth?.div(2) as Int
        val top = height/2 - drHeight?.div(2) as Int
        // 이미지 그리기
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo),left.toFloat(), top.toFloat(), null)
    }

    // 각 항목을 꾸미기 위해서 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 항목의 index 획득..
        val index = parent.getChildAdapterPosition(view) + 1 // 계산의 편의성을 위해 +1
        if(index % 3 == 0)
            outRect.set(10,10,10,80)
        else
            outRect.set(10,10,10,0)
        view.setBackgroundColor(Color.parseColor("#28A0FF"))
        ViewCompat.setElevation(view, 20.0f)
    }
}
class OneFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater, container, false)

        //리사이클러뷰 데이터 준비
        val datas = mutableListOf<String>()

        // 전통적인 언어처럼 for(;;) 스타일은 제공하지 않는다.
        // in은 범위 연산자
        for(i in 1..20){
            datas.add("Item $i")
        }
        // 리사이클러 뷰 준비
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = MyAdapter(datas)
            addItemDecoration(MyDecoration(activity as Context))
        }
        return binding.root
    }
}