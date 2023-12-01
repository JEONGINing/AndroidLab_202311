package com.example.ch15_retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Application 클래스.. 앱 내에 하나만, 프로세스 시작 시 가장 먼저 singleton으로 생성해 주는 클래스..
// 앱 전역에서 공유해야 하는 상수 변수나 데이터
// 앱이 시작하면서 최초 한번 진행해야 하는 업무

// Application 클래스는 manifest 등록해야...
class MyApplication: Application() {
    var networkService: NetworkService

    // 자바의 변수는 field(데이터만 가지는), 코틀린의 변수는 property(getter, setter 내장)
    // 원한다면 custom getter, setter 등록 가능
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    init {
        // 클래스 reference를 줄 때, A::class, A::class.java
        // 이 reference가 대입될 api가 코틀린에서 선언한 것이라면, A::class
        // 자바라면 A::class.java
        // 코틀린과 자바의 클래스 reference 타입이 달라서..
        networkService = retrofit.create(NetworkService::class.java)
    }
}