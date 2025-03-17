package com.allephnogueira.altapressaognvpro.viewmodel

import android.content.Context
import android.widget.Toast

class ExibirMensagem {
    companion object {
        fun exibirMensagem(context: Context ,mensagem: String) {
            Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
        }
    }
}