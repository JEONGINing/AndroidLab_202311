package com.example.ch8_menu

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 메뉴 구현을 위해 자동 호출
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // 이 코드 만으로 xml의 메뉴가 화면에 출력된다.

        //SearchView 객체 획득. SearchView가 적용된 MenuItem 객체를 먼저 얻고, 그곳에 등록된 SearchView 획득
        val menuItem = menu?.findItem(R.id.menu3)
        //코틀린의 명시적 캐스팅 연산자 - as
        val searchView = menuItem?.actionView as SearchView

        //UI적인 다양한 처리가 된다.

        //검색 이벤트
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            //검색어를 입력하기 위해서 올라온 키보드의 검색버튼을 누른 순간
            override fun onQueryTextSubmit(query: String?): Boolean {
                //this@MainActivity - 자바에서는 MainActivity.this
                Toast.makeText(this@MainActivity,query,Toast.LENGTH_SHORT).show()
                searchView.setQuery("", false)
                searchView.isIconified = true
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    //메뉴 이벤트 처리 함수
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu1) {
            Log.d("kkang", "menu1 click...")
        }
        return super.onOptionsItemSelected(item)
    }
}