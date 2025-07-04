package com.allephnogueira.altapressaognvpro.model

import android.content.Context
import android.content.Intent
import com.allephnogueira.altapressaognvpro.view.MainActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private val autenticador by lazy { FirebaseAuth.getInstance() }
private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }

class VerificarUsuarioLogado {
    companion object {
        fun verifcarUsuarioLogado(context: Context) {
            val usuario = FirebaseAuth.getInstance()

            if (usuario.currentUser != null) {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }

        }


    }
}