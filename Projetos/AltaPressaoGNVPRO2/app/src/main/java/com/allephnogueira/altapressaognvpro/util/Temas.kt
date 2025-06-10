package com.allephnogueira.altapressaognvpro.util

import android.content.Context
import android.util.Log
import com.allephnogueira.altapressaognvpro.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions


object Temas {
    fun temaEscuro(context: Context, map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
            )
            if (!success) {
                Log.e("MAPA", "Falha ao aplicar tema escuro.")
            }
        } catch (e: Exception) {
            Log.e("MAPA", "Arquivo de estilo não encontrado", e)
        }
    }
}
