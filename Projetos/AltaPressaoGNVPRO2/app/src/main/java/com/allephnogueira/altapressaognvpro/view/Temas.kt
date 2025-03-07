package com.allephnogueira.altapressaognvpro.view

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.allephnogueira.altapressaognvpro.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions

class Temas {
    companion object {
        fun temaEscuro (context: Context, googleMap: GoogleMap) {
            // Apply dark theme to map
            try {
                val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
                )
                if (!success) {
                    Log.e("MapsActivity", "Failed to apply map style.")
                }
            } catch (e: Resources.NotFoundException) {
                Log.e("MapsActivity", "Map style resource not found.", e)
            }
        }
    }
}