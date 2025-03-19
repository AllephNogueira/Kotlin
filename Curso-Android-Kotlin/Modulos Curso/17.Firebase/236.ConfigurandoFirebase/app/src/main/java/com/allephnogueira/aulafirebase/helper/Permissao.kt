package com.allephnogueira.aulafirebase.helper

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissao {

    companion object {

        fun requisitarPermissoes(activity : Activity, permissoes: List<String>, requestCode : Int){
            /*Verificar as permissoes negadas
            * Esse Check ele vai fazer uma verificação individual de cada permissao
            * check = checar
            *
            * Esse metodo ele checa cada permissao de modo individual
            * E o parametro esta passando uma lista, então vamos percorrer essa lista e verificar cada permissao de forma individual
            * Se a permissao for concedida ele retorna ( 0 ) PER... GRANTED
            * Se for negada ele retorna ( -1 ) PER... DENIED
            *
            * temPermisao é onde vai ficar o valor 0 ou -1
            *
            * Agora vamos verificar se a permissao é aceita == 0  /   PackageManager.PERMISSION_GRANTED  /
            *
            * Vamos também criar o permissoesNegadas para ir adicionando as permissoes que o usuario nao quis dar.
            *
            * Lembrar que o temPermissoes é um boleano, então ele vai retornar sempre um verdadeiro ou falso
            * */

            val permissoesNegadas = mutableListOf<String>()

            permissoes.forEach { permissao ->


                val temPermisao = ContextCompat.checkSelfPermission(
                    activity,
                    permissao
                ) == PackageManager.PERMISSION_GRANTED // Verificar se foi aceita a permissao

                if ( !temPermisao ) {
                    /* Aqui estou usando negação
                    Então sempre quando a permissao for false ele vai cair aqui dentro.
                    E vai guardando as permissoes que foram negadas "false"
                     */

                    permissoesNegadas.add(permissao)

                }

                /* Agora so vamos usar aqui, se tiver permissoes negadas
                * Se tiver permissoes negadas ai sim, pedimos novamente
                * Resumindo se o usuario ja deu a permissao, nao vamos pedir novamente
                * Mas se essa permissao estiver NEGADA vamos pedir novamente.*/
                if (permissoesNegadas.isNotEmpty()) {
                    ActivityCompat.requestPermissions(
                        activity,
                        permissoesNegadas.toTypedArray(),
                        requestCode
                    )
                }
            }


            /* Aqui vamos requisitar as permissoes negada.
           Sempre quando o usuario baixa e instala o app pela primeira vez, todas as permissoes vem como negada por padrao.

         Aqui vamos precisar de 3 parametros
        1 Activity que vai pedir a permisao
        2 Uma lista de permissoes - Permissoes vai ser uma lista de Strings
        3 Requestcode - ja vamos deixar como padrao 0
         */
//            ActivityCompat.requestPermissions(
//                activity, // Primeiro parametro
//                permissoes.toTypedArray(), // Aqui ele espera receber um Array, e no parametro passamos List // Por esse motivo estamos convertendo uma list para array
//                0
//            )
        }
    }

}