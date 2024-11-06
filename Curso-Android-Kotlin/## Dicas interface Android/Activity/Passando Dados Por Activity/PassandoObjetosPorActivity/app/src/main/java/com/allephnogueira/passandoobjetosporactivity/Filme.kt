package com.allephnogueira.passandoobjetosporactivity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacoes: Double,
    val direitor: String,
    val distribuidor: String
) : Parcelable


//data class Filme(
//    val nome: String,
//    val descricao: String,
//    val avaliacoes: Double,
//    val direitor: String,
//    val distribuidor: String
//) : Serializable    // Precisamos dizer que filme é do tipo Serializable para poder usar no putExtra
