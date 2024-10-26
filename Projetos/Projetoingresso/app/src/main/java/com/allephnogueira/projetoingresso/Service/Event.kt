package com.allephnogueira.projetoingresso.Service


data class EventResponse(
    val items: List<Event>
)

data class Event(
    val id: String,
    val title: String,
    val premiereDate: PremiereDate, // Aqui é para incluir data de estreia.
    val synopsis: String,
    val images: List<Image>,
    val genres: List<String> // Genros vem como uma lista de strings
)


data class PremiereDate(
    val localDate: String,
    val isToday: Boolean,
    val dayOfWeek: String,
    val dayAndMonth: String,
    val hour: String,
    val year: String
)

data class Image(
    val url: String
    // Aqui tinha uma virgula.
)


