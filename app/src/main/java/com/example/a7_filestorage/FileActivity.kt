package com.example.a7_filestorage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.file.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.Buffer

class FileActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file)

        val inputText = load()
        if(inputText.isNotEmpty()){
            file_editText.setText(""+inputText)
            file_editText.setSelection(inputText.length)        //移动光标到最后
            Toast.makeText(this,"上次的数据已经恢复",Toast.LENGTH_SHORT).show()
        }
    }

    //销毁时保存数据
    override fun onDestroy() {
        super.onDestroy()
        val inputText = file_editText.text.toString()
        save(inputText)
    }

    //文本存储:写入和读出
    fun save(inputText: String){
        try{
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)         //use函数会自动关闭外层的流
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        Log.d("www","数据已保存")
    }

    fun load(): String {

        val content = StringBuilder()

            try{
                val input = openFileInput("data")
                val reader = BufferedReader(InputStreamReader(input))
                reader.use{
                    reader.forEachLine {
                        content.append(it)
                    }
                }
            }catch (e: IOException){
                e.printStackTrace()
            }
        Log.d("www","数据已取出")
        return content.toString()
    }
}