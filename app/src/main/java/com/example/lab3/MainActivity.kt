package com.example.lab3

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity()
{

    private lateinit var dBHelper: DataBaseHelper
    private lateinit var adapter: CustomCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dBHelper = DataBaseHelper(applicationContext)
        adapter = CustomCursorAdapter(applicationContext, dBHelper.readData())
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            File.createTempFile(
                "${view.item_timestamp.text}_",
                ".txt",
                Environment.getExternalStorageDirectory()
            )?.run {
                FileOutputStream(this)
            }?.also {
                it.write(view.item_data.text.toString().toByteArray())
            }?.run {
                Toast.makeText(applicationContext, "Zapisano ${view.item_data.text}", Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            dBHelper.insertData(editText.text.toString())
            adapter.changeCursor(dBHelper.readData())
        }


    }
}
