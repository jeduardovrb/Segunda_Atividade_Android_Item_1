package com.photos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import photosG

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {
            val albumId = txt_album_id.text.toString()
            val id = txt_id.text.toString()
            val title = txt_title.text.toString()
            val url = txt_url.text.toString()
            val thumbnailUrl = txt_thumbnail_url.text.toString()

            if (albumId.isNotEmpty() && id.isNotEmpty() && title.isNotEmpty()) {
                val photo = Photo(id.toInt(), albumId.toInt(), title, url, thumbnailUrl)
                photosG.add(photo)
                txt_album_id.text.clear()
                txt_id.text.clear()
                txt_title.text.clear()
                txt_url.text.clear()
                txt_thumbnail_url.text.clear()
            } else {
                txt_album_id.error = if (txt_album_id.text.isEmpty()) "Digitar o id do album" else null
                txt_id.error = if (txt_id.text.isEmpty()) "Digitar o id da foto" else null
                txt_title.error = if (txt_title.text.isEmpty()) "Digitar o titulo" else null
            }

            finish();
        }
    }
}