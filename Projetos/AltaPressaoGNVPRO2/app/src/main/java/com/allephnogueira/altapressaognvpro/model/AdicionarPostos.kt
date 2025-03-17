package com.allephnogueira.altapressaognvpro.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.allephnogueira.altapressaognvpro.view.MapsActivity
import com.google.firebase.firestore.FirebaseFirestore

private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }

class AdicionarPostos(
    var idPosto: String,
    var latitude: String,
    var longitude: String,
    var idUsuario: String,
    var nomeUsuario: String,
    var tipoCombustivel: String,
    var bandeiraDoPosto: String,

    ) {

    companion object {
        fun adicionarPosto(dados: AdicionarPostos, tipoDeServico: String) {


            val dadosMap = mapOf(
                "idPosto" to dados.idPosto,
                "latitude" to dados.latitude,
                "longitude" to dados.longitude,
                "idUsuario" to dados.idUsuario,
                "nomeUsuario" to dados.nomeUsuario,
                "tipoCombutisvel" to dados.tipoCombustivel,
                "bandeiraPosto" to dados.bandeiraDoPosto
            )

            /* nomeDocumento
            Vamos salvar o posto de acordo com as 3 primeiras letras da sua latitude.
            Exemplo se são gonçalo for 130 esse vai ser o nome do documento de são gonçalo
            Dessa forma o usuario só vai poder ver os dados da sua propria regiao
             */
            var nomeDocumento = dados.latitude




            bancoDeDados.collection(tipoDeServico)
                .document(nomeDocumento)
                .set(dadosMap)
                .addOnSuccessListener {
                    Log.i("info_AdicionarPosto", "Posto cadastrado com: ${dados.latitude}")
                }.addOnFailureListener {
                    Log.i("info_AdicionarPosto", "ERRO: Não conseguimos adicionar o posto.")
                }

        }

    }

}