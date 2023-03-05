package com.snick.testvkapp.data

import android.content.SharedPreferences
import javax.inject.Inject

interface PreferenceDataSource {

    fun saveLastQuery(query: String)
    fun readLastQuery(): String

    class Base @Inject constructor (private val sharedPreferences: SharedPreferences): PreferenceDataSource{

        override fun saveLastQuery(query: String) {
            sharedPreferences.edit().putString(KEY_QUERY,query).apply()
        }

        override fun readLastQuery(): String {
            return sharedPreferences.getString(KEY_QUERY,"funny")!!
        }


        private companion object{

            const val KEY_QUERY = "QUERY"
        }
    }
}