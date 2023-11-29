package com.example.ch7_database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 자바처럼 클래스명과 동일한 이름으로 생성자를 사용하지는 않는다. constructor 예약어 함수가 생성자.

// 생성자가 주생성자(클래스 선언위치에 선언 - 1개만 추가 가능, constructor 예약어 생략 가능),
// 보조생성자(클래스 바디에 constructor 예약어로 여러개 선언 가능)로 구분.

// 클래스 선언에 : 뒤에 상위 클래스 혹은 인터페이스 나열. 단일 상속, 인터페이스는 여러개, 선언 순서는 없다.
// SQLiteOpenHelper()에서 ()는 상위 클래스 생성자 호출 구문이다.
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb", null, 1){

    // app install 후 최초 한번
    override fun onCreate(p0: SQLiteDatabase?) {
        // null safety는 타입 이야기. 타입적으로 nullable과 non-null 구분
        // val obj: User -> non-null / vall obj: User? - nullable
        // nullable로 선언된 객체의 멤버 접근은 null safety operator 이용해야
        p0?.execSQL("create table TODO_DB (" +
                    "_id integer primary key autoincrement," +
                    "todo not null)")
    }

    // db version 변경할 때마다
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}