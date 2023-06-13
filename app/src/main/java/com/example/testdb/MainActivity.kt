package com.example.testdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.testdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this,1)

        binding.btnSave.setOnClickListener {
            dbHelper.insert(binding.editTxt1.text.toString(), Integer.parseInt(binding.editTxt2.text.toString()), binding.editTxt3.text.toString())
        }

        binding.btnSelect.setOnClickListener {
            binding.resultSelect.text = getFormattedResult(dbHelper.result)
        }


    }

    fun getFormattedResult(result : ArrayList<ArrayList<Any>>): String{
        var formattedResult = ""

        result.forEach {
            formattedResult += " 이름 : ${it[0]}, 나이 : ${it[1]}, 주소 : ${it[2]}\n"
        }

        return formattedResult
    }
}