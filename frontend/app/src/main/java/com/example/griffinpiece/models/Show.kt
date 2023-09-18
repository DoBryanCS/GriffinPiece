package com.example.griffinpiece.models

data class Show(
    var id: Int,
    var title: String,
    var description: String,
    var imageUrl: String,
    var releaseDate: String,
    var genre: String,
    var rating: Float
)