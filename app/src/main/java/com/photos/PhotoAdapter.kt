package com.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PhotoAdapter(contexto: Context): ArrayAdapter<Photo>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vVisao:View

        if(convertView != null) {
            vVisao = convertView
        } else {
            vVisao = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        val txt_title  = vVisao.findViewById<TextView>(R.id.txt_title)

        val photo = this.getItem(position)

        if (photo != null) {
            txt_title.text = photo.title
        }

        return vVisao
    }
}