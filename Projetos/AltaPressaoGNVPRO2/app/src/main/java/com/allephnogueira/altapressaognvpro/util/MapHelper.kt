package com.allephnogueira.altapressaognvpro.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat
import com.allephnogueira.altapressaognvpro.R
import com.allephnogueira.altapressaognvpro.model.Local
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapHelper(private val map: GoogleMap) {

    @SuppressLint("MissingPermission")
    fun ativarLocalizacao(context: Context) {
        if (ActivityCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
        }
    }

    fun moverCameraPara(local: LatLng, zoom: Float = 15f) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, zoom))

    }

    // Função privada para redimensionar ícone
    private fun getResizedBitmap(context: Context, resourceId: Int, width: Int, height: Int): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }

    // Agora recebe Context para poder redimensionar o ícone
    fun adicionarMarcador(context: Context, latitude: Double, longitude: Double, titulo: String, local: Local) {
        val latLng = LatLng(latitude, longitude)

        val avaliacao = (local.avaliacao ?: "0").toDoubleOrNull() ?: 0.0

        val icone = when {
            avaliacao < 1 -> R.drawable.icon0
            avaliacao < 2 -> R.drawable.icon_estrela_1
            avaliacao < 3 -> R.drawable.icon_estrela_2
            avaliacao < 4 -> R.drawable.icon_estrela_3
            avaliacao < 4.5 -> R.drawable.icon_estrela_4
            else -> R.drawable.icon_estrela_5
        }


        val tamanhoEmPx = 170

        val bitmapRedimensionado = getResizedBitmap(context, icone, tamanhoEmPx, tamanhoEmPx)

        val markerOptions = MarkerOptions()
            .position(latLng)
            .title(titulo)
            .icon(BitmapDescriptorFactory.fromBitmap(bitmapRedimensionado))

        map.addMarker(markerOptions)
    }
}
