package com.photos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.photos.R
import kotlinx.android.synthetic.main.activity_main.*
import photosGlobal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PhotoAdapter(this)
        list_view_photo.adapter = adapter

        btn_add_photo.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btn_get_photo.setOnClickListener {
            this.carregarPhotos()
            val adapter = list_view_photo.adapter as PhotoAdapter
            adapter.clear()
            adapter.addAll(photosGlobal)
        }

//        btn_rem_photo.setOnClickListener {
//            val item = adapter.getItem(position)
//            adapter.remove(item)
//            photosGlobal.remove(item)
//        }

        list_view_photo.setOnItemLongClickListener {
                adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = adapter.getItem(position)
            adapter.remove(item)
            photosGlobal.remove(item)
            return@setOnItemLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_photo.adapter as PhotoAdapter
        adapter.clear()
        adapter.addAll(photosGlobal)
    }

    fun carregarPhotos() {
        try {
            val fotos = HttpService().execute().get()
            photosGlobal.clear()
            photosGlobal.addAll(fotos)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}