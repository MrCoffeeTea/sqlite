package com.example.a7_filestorage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button1.setOnClickListener {
            val intent = Intent(this, FileActivity::class.java)
            startActivity(intent)
            Log.d("www","打开了File Activity")
        }
        button2.setOnClickListener {
            val intent = Intent(this, SharedPreferencesActivity::class.java)
            startActivity(intent)
            Log.d("www","打开了 Activity")
        }

        button3.setOnClickListener {
            val intent = Intent(this, SQLActivity::class.java)
            startActivity(intent)
            Log.d("www","打开了SQLActivity")
        }
    }


}