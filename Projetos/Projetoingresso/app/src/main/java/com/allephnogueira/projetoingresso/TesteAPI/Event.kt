package com.allephnogueira.projetoingresso.TesteAPI


data class EventResponse(
    val items: List<Event>
)

data class Event(
    val id: String,
    val title: String,
    val date: String,
    val images: List<Image>
)

data class Image(
    val url: String
)
