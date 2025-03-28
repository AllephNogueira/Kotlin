package com.allephnogueira.whatsapp.utils

import android.app.Activity
import android.widget.Toast

/* Aqui vamos criar extensoes para a gente usar no nosso aplicativo
Vamos criar a partir de Activity
Aqui podemos usar o this, porque estamos criando uma extensao apartir da Activity, então ele vai sempre pegar o contexto da Activity

Essas extensoes vai permitir a gente usar em qualquer activity

 */
fun Activity.exibirMensagem(mensagem: String) {
    Toast.makeText(this,
        mensagem,
        Toast.LENGTH_LONG)
        .show()
}