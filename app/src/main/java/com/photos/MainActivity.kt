package com.photos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import photosGlobal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PhotoAdapter(this)
        list_Grade.adapter = adapter

        btn_adicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btn_Consultar.setOnClickListener {
            this.carregarPhotos()
            val adapter = list_Grade.adapter as PhotoAdapter
            adapter.clear()
            adapter.addAll(photosGlobal)
        }

        list_Grade.setOnItemLongClickListener {
                adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = adapter.getItem(position)
            adapter.remove(item)
            photosGlobal.remove(item)
            return@setOnItemLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_Grade.adapter as PhotoAdapter
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