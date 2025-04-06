package com.allephnogueira.whatsapp.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Mensagem (
    val idUsuario: String = "",
    val mensagem: String = "",
    @ServerTimestamp // Aqui quer dizer, Servidor Tempo(Data, hora, minuto...)
    // Ou seja quando for salvar a data da mensagem, vai salvar com a data e hora do servidor.
    val data: Date? = null
)