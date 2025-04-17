package com.allephnogueira.altapressaognvpro.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class DadosDaLocalizacao(
    var idDoLocal: String = "", // Id do local vai ser sempre os 5 primeiros digitos da localização.
    var latitude: String = "",
    var longitude: String = "",
    var nomePosto: String = ""
)