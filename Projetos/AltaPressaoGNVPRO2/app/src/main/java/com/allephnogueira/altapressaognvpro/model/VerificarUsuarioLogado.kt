package com.allephnogueira.altapressaognvpro.model

import android.content.Context
import android.content.Intent
import com.allephnogueira.altapressaognvpro.view.MapsActivity
import com.google.firebase.auth.FirebaseAuth

class VerificarUsuarioLogado {
    companion object {
        fun verifcarUsuarioLogado(context: Context) {
            val usuario = FirebaseAuth.getInstance()

            if (usuario.currentUser != null) {
                val intent = Intent(context, MapsActivity::class.java)
                context.startActivity(intent)
            }

        }
    }
}