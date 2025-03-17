package com.allephnogueira.altapressaognvpro.model

data class Localizacoes(
    var idDoLocal: String, // Id do local vai ser sempre os 5 primeiros digitos da localização.
    var latitude: String,
    var longitude: String,
    var nomePosto: String
)