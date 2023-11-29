package com.example.ch5_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch5_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //코틀린은 타입이 후행한다. 타입이 후행하면 타입 유추 기법을 제공할 수 있다.
    //타입이 없다는 것이 아니라, 대입되는 값을 보고 유추가 가능하다면 선언하지 않아도 된다.

    //back button을 누른 시간 저장
    var initTime = 0L
    // chronometer 멈춤 시간 저장
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //익명 클래스. 코틀린에서 익명 클래스는 object 예약어로.
        //object {}
        //object A {} - 익명 클래스를 선언하고 그 클래스의 객체를 A라는 이름으로 선언. 싱글톤 객체 만들 때 일반적으로 이용.
        //object: A {} - 이름은 없지만, : 뒤에 상위클래스 혹은 인터페이스 명시, 타입 명시.
        //companion object {} - static 예약어는 없다. companion object 내에 선언한 멤버(변수, 함수)를 outer 클래스명으로 접근. static 효과.
        binding.startButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                binding.chronometer.start()

                binding.startButton.isEnabled = false
                binding.stopButton.isEnabled = true
                binding.resetButton.isEnabled = true
            }
        })

        //인터페이스를 구현한 클래스를 선언할 때, 추상함수가 하나만 있다면 추상함수 내부의 내용만 람다함수로 선언 가능
        //추상함수 하나의 인터페이스만 가능. 여러개는 불가능.
        //Single Abstract Method 기법.
        binding.stopButton.setOnClickListener({
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
        })

        //코틀린의 함수는, 다른 함수를 매개변수로, 다른 함수를 리턴값으로 활용하기에 유연함. Hof(High Order Function)
        //함수를 호출하면서 매개변수는 () 안에다가.
        //그런데 마지막 매개변수만, 그 타입이 함수라면 () 밖에 선언 가능.
        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
        }

//        a(10, true, {no->no*10})
        // 함수의 마지막 매개변수만, 그 매개변수가 함수라면, () 밖에 선언 가능.
//        a(10, true) {
//
//        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

//    fun a(arg1:Int, arg2:Boolean, arg3:(Int)->Unit){
//
//    }
}