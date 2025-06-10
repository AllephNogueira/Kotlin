package com.allephnogueira.altapressaognvpro.utils

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class LocationPermissionHelper(
    private val fragment: Fragment,
    private val onPermissaoConcedida: () -> Unit
) {
    companion object {
        const val CODIGO_PERMISSAO = 1001
    }

    fun verificarOuSolicitarPermissao() {
        if (ContextCompat.checkSelfPermission(
                fragment.requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fragment.requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), CODIGO_PERMISSAO
            )
        } else {
            onPermissaoConcedida()
        }
    }

    fun tratarResultadoPermissao(requestCode: Int, grantResults: IntArray) {
        if (requestCode == CODIGO_PERMISSAO) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissaoConcedida()
            } else {
                Toast.makeText(fragment.requireContext(), "Permissão negada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
