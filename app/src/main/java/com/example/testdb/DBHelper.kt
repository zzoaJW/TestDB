package com.example.testdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, null, version) {

    // Person Table 생성
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Person(name TEXT, Age INT, ADDR TEXT)")
    }

    // Person Table Upgrade
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Person")
        onCreate(db)
    }

    // Person Table 데이터 입력
    fun insert(name: String, age: Int, Addr: String) {
        val db = writableDatabase
        db.execSQL("INSERT INTO Person VALUES('$name', $age, '$Addr')")
        db.close()
    }

    // Person Table 데이터 수정
    fun Update(name: String, age: Int, Addr: String) {
        val db = writableDatabase
        db.execSQL("UPDATE Person SET age = $age, ADDR = '$Addr' WHERE NAME = '$name'")
        db.close()
    }

    // Person Table 데이터 삭제
    fun Delete(name: String) {
        val db = writableDatabase
        db.execSQL("DELETE Person WHERE NAME = '$name'")
        db.close()
    }// 읽기가 가능하게 DB 열기

    // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
    // Person Table 조회
    val result: String
        get() {
            // 읽기가 가능하게 DB 열기
            val db = readableDatabase
            var result = ""

            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            val cursor = db.rawQuery("SELECT * FROM Person", null)
            while (cursor.moveToNext()) {
                result += """ 이름 : ${cursor.getString(0)}, 나이 : ${cursor.getInt(1)}, 주소 : ${
                    cursor.getString(
                        2
                    )
                }
"""
            }
            return result
        }

    companion object {
        const val DATABASE_NAME = "test.db"
    }
}