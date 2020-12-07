package com.photos

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String? = null,
    val thumbnailUrl: String? = null
)