package com.allephnogueira.aulafirebase.helper

import android.app.Activity
import androidx.core.app.ActivityCompat

class Permissao {

    companion object {


        /* Aqui vamos precisar de 3 parametros
        1 Activity que vai pedir a permisao
        2 Uma lista de permissoes - Permissoes vai ser uma lista de Strings
        3 Requestcode - ja vamos deixar como padrao 0
         */
        fun requisitarPermissoes(activity : Activity, permissoes: List<String>){
            /* Aqui vamos requisitar as permissoes negada.
             Sempre quando o usuario baixa e instala o app pela primeira vez, todas as permissoes vem como negada por padrao.

             */
            ActivityCompat.requestPermissions(
                activity, // Primeiro parametro
                permissoes.toTypedArray(), // Aqui ele espera receber um Array, e no parametro passamos List // Por esse motivo estamos convertendo uma list para array
                0
            )
        }
    }

}