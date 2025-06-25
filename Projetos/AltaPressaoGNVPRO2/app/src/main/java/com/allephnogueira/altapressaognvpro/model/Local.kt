package com.allephnogueira.altapressaognvpro.model

data class Local(
    var idDoLocal: String? = null, // Id do local vai ser sempre os 5 primeiros digitos da localização.
    var latitude: String? = null,
    var longitude: String? = null,
    var nomePosto: String? = null,
    var nomeUsuario: String? = null,
    var mediaDasAvaliacoes: String? = null, // Media da avaliacao (Quantidade de avaliadores ex 100 /  somaTotalDasAvaliacoes ex 700)
    var somaTotalDasAvaliacoes: String? = null, // QUantidade de pessoas que avaliaram
    var quantidadeDeAvaliadores: String? = null,
)