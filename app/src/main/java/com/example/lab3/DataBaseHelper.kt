package com.example.lab3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "lab3.db", null, 1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS ${Companion.Contract.Entry.TABLE_NAME} " +
            "(${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "${Companion.Contract.Entry.COLUMN_NAME_DATA} TEXT, " +
            "${Companion.Contract.Entry.COLUMN_NAME_TIMESTAMP} DATETIME DEFAULT CURRENT_TIMESTAMP)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        db?.execSQL("DROP TABLE IF EXISTS LAB3")
        onCreate(db)
    }

    fun insertData(data: String?) =
            ContentValues()
                .apply {
                    put(Companion.Contract.Entry.COLUMN_NAME_DATA, data)
                }
                .run {
                    writableDatabase?.insert(Companion.Contract.Entry.TABLE_NAME, null, this)
                }

    fun readData() =
            readableDatabase
                ?.rawQuery("SELECT * from ${Companion.Contract.Entry.TABLE_NAME}", null)

    companion object
    {
        object Contract
        {
            object Entry : BaseColumns
            {
                const val TABLE_NAME = "LAB3"
                const val COLUMN_NAME_DATA = "data"
                const val COLUMN_NAME_TIMESTAMP = "timestamp"
            }
        }
    }
}
