package com.example.ch15_retrofit

import com.google.gson.annotations.SerializedName

// 클래스가 데이터를 표현하기 위한 VO, DTO, Entity라면 과감하게 data 예약어 추가
// 코틀린은 파일명과 클래스명이 전혀 관련 없다..
data class User(
    var id:String,
    @SerializedName("first_name")
    var firstName:String,
    @SerializedName("last_name")
    var lastName:String,
    var avatar:String // 이미지 다운로드 url
)

data class UserList(
    var page: String,
    var data: List<User>?
)