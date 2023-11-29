package com.example.ch7_settings

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

// Fragment 클래스. activity가 출력해줘야 한다.
class MySettings: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // 아래의 한 줄만으로도, 설정 화면 및 저장까지 자동으로.
        setPreferencesFromResource(R.xml.settings, rootKey)



        // 아래의 코드들은 필요하면 사용

        // key로 설정 객체 획득
        val idPreference: EditTextPreference? = findPreference("id")
        val colorPreference: ListPreference? = findPreference("color")

        // summary를 동적으로 저장 해보기

        // 1. 설정 값을 그대로 summary에 지정하면 되는 경우에는..
        colorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        // 2. 설정 값을 이용해 summary 문자열을 알고리즘으로 만들고 싶은 경우에는..
         idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
             // 유저 설정값 획득
             val id = it.text
             if(TextUtils.isEmpty(id)) {
                 // 리턴 문자열. 람다함수 내에서는 return 사용 못 한다.
                 "설정이 되지 않았습니다."
             } else {
                 "설정한 ID는 $id 입니다."
             }
         }

        idPreference?.setOnPreferenceChangeListener { preference, newValue ->
            Log.d("kkang", "key: ${preference.key}, value: $newValue")
            true
        }
    }
}