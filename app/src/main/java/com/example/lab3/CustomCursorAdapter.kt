package com.example.lab3

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class CustomCursorAdapter(context: Context?, cursor: Cursor?) : CursorAdapter(context, cursor, 0)
{
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View =
        LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

    override fun bindView(view: View?, context: Context?, cursor: Cursor?)
    {
        view?.item_data?.text = cursor?.getString(cursor.getColumnIndex(DataBaseHelper.Companion.Contract.Entry.COLUMN_NAME_DATA))
        view?.item_timestamp?.text = cursor?.getString(cursor.getColumnIndex(DataBaseHelper.Companion.Contract.Entry.COLUMN_NAME_TIMESTAMP))
    }
}