package com.allephnogueira.altapressaognvpro.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


private val bancoDeDados by lazy { FirebaseFirestore.getInstance() }


class CapturarUsuarioLogado {
    companion object {
        suspend fun capturarUsuarioLogado(idUsuarioLogado: String): Usuario? {
            return suspendCoroutine { continuation ->
                val referenciaUsuario = bancoDeDados
                    .collection("usuarios")
                    .document(idUsuarioLogado)

                referenciaUsuario.addSnapshotListener { documentSnapshot, erro ->
                    if (erro != null) {
                        continuation.resume(null) // Continuar com erro
                        return@addSnapshotListener
                    }

                    val dados = documentSnapshot?.data
                    if (dados != null) {
                        val dadosComoString = dados.mapValues { it.value.toString() }
                        val usuarioLogado = Usuario(
                            dadosComoString["id"],
                            dadosComoString["email"],
                            dadosComoString["senha1"],
                            dadosComoString["senha2"],
                            dadosComoString["nome"],
                            dadosComoString["sobrenome"],
                            dadosComoString["telefone"],
                            dadosComoString["anoNascimento"]
                        )
                        continuation.resume(usuarioLogado) // Continuar com sucesso
                    } else {
                        continuation.resume(null) // Continuar com valor nulo
                    }
                }
            }
        }
    }
}
