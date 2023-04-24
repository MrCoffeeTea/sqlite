package com.example.a7_filestorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

//数据库帮助类用于实现数据库操作
class myDataBaseHelper(val context:Context, name:String, version: Int)
        : SQLiteOpenHelper(context, name, null, version) {

    private val createDB:String = "create table Student(id integer primary key autoincrement," +
            "name text," +
            "age integer," +
            "score real)"

    private val createCategory = "create table Category(" +
            "id integer primary key autoincrement, " +
            "category_name text)";

    //创建表
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createDB)
        Log.d("www","表创建成功")
    }

    //数据根据版本号更新
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion <= 1){            //版本<=1新建表
            db?.execSQL(createCategory)
        }
        if(oldVersion <= 2){            //如果版本<=2新增一列
            db?.execSQL("alter table Student add column majory text")
        }
    }

}