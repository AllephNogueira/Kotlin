package com.allephnogueira.altapressaognvpro.model

data class Local(
    var idDoLocal: String? = null, // Id do local vai ser sempre os 5 primeiros digitos da localização.
    var latitude: String? = null,
    var longitude: String? = null,
    var nomePosto: String? = null,
    var nomeUsuario: String? = null,
    var avaliacao: String? = null
)