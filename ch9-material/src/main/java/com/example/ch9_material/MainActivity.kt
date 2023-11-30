package com.example.ch9_material

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch9_material.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    // ViewPager의 Adapter, 항목(화면)을 만들어 주는 역할
    class MyFragmentAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        //멤버변수는 자동 초기화가 안 된다.

        // 선언과 동시에 초기값을 대입하던가,
        // nullable로 선언하고 null을 대입하던가,
        // 초기화를 미루고 싶으면 lateinit으로 선언하던가,
        // 생성자 초기화 블락인 init{} 에서 초기화 하던가..

        val fragments: List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        // 항목 갯수를 판단하기 위해서 자동 호출
        override fun getItemCount(): Int {
            return fragments.size
        }

        // 항목을 구성하기 위해서 자동 호출. 매개변수의 index 값을 보고 적절한 항목을 구성해서 리턴.
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // toolbar에 actionbar 내용 적용
        setSupportActionBar(binding.toolbar)

        // ViewPager...........
        binding.viewpager.adapter = MyFragmentAdapter(this)

        // toggle
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // tablayout
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = "Tab${position+1}"
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // toggle 버튼이 내부적으로 메뉴로 추가되는 것이어서.. 메뉴 이벤트 함수가 호출된다.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}