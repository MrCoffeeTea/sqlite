package com.example.a7_filestorage

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.sql.*

//建立表Student,含有id,name,age,score列
class SQLActivity: AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sql)

        val dbHelper = myDataBaseHelper(this, "Student.db", 1)

        btn_create.setOnClickListener {
            dbHelper.writableDatabase           //建立数据库
        }

        //插入数据
        btn_insert.setOnClickListener {
            val db = dbHelper.writableDatabase      //获取数据库
            val value1 = ContentValues().apply {

                val _name : String = name.text.toString()
                val _age :Int = age.text.toString().toInt()
                val _score = score.text.toString().toDoubleOrNull()

                put("name", _name)
                put("age",_age)
                put("score",_score)
            }
            db.insert("Student",null,value1)
            Log.d("insert","插入了一个学生数据")

            //清空文本框
            name.setText("")
            age.setText("")
            score.setText("")
        }

        //删除数据
        btn_delete.setOnClickListener {
            val db = dbHelper.writableDatabase
            val _name : String = name.text.toString()

            //弹出提示框
            AlertDialog.Builder(this).apply {
                setTitle("删除中...")
                setMessage("你确定删除${_name}的信息吗?")
                setCancelable(true)
                setPositiveButton("确定"){
                    _, _ ->
                    db.delete("Student", "name = ?", arrayOf("${_name}"))    //开除不及格的学生
                    Log.d("delete","删除了学生${_name}的数据")
                }
                setNegativeButton("取消"){
                    _, _ ->
                }
                show()
            }

        }

        //查询数据
        btn_search.setOnClickListener {

            val db = dbHelper.writableDatabase

            stuInfo.visibility = View.VISIBLE
            showID.setText("ID")
            showName.setText("姓名")
            showAge.setText("年龄")
            showScore.setText("分数")

            //查询Student表中的所有数据
            val cursor = db.query("Student", null,null, null, null, null, null)
            if(cursor.moveToFirst()){
                do{
                    //获取列索引号
                    val id = cursor.getString( cursor.getColumnIndex("id") )
                    val name = cursor.getString( cursor.getColumnIndex("name") )
                    val age = cursor.getString( cursor.getColumnIndex("age") )
                    val score = cursor.getString( cursor.getColumnIndex("score") )
                    Log.d("StudentInfo: ","The $id student name is $name, age is $age,score is $score")

                    showID.setText("${showID.text}\n${id}")
                    showName.setText("${showName.text}\n${name}")
                    showAge.setText("${showAge.text}\n${age}")
                    showScore.setText("${showScore.text}\n${score}")

                }while (cursor.moveToNext())        //行索引后移
            }
            cursor.close()
        }


        //更新数据库:把id重置
        btn_update.setOnClickListener {
            val db = dbHelper.writableDatabase
           // val values = ContentValues()
           //values.put("score",60)
           // db.update("Student", values, "score < ?", arrayOf("60"))

            AlertDialog.Builder(this).apply {
                setTitle("删除中...")
                setMessage("你确定重置学生表吗?")
                setCancelable(true)
                setPositiveButton("确定"){
                        _, _ ->
                    db.execSQL("drop table Student")
                    db.execSQL("create table Student (id integer primary key autoincrement, name text, age integer, score real) ")
                    Log.d("delete","学生表已格式化")
                }
                setNegativeButton("取消"){
                        _, _ ->
                }
                show()
            }

        }

    }




}