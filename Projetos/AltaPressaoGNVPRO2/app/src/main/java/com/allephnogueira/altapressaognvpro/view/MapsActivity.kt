package com.allephnogueira.altapressaognvpro.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.allephnogueira.altapressaognvpro.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.allephnogueira.altapressaognvpro.databinding.ActivityMapsBinding
import com.google.firebase.auth.FirebaseAuth

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    // Inicialização das variáveis
    private val binding by lazy { ActivityMapsBinding.inflate(layoutInflater) }
    private val autenticador by lazy { FirebaseAuth.getInstance() }
    private lateinit var mapa: GoogleMap
    private lateinit var clienteLocalizacao: FusedLocationProviderClient
    private val CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Obter o SupportMapFragment e notificar quando o mapa estiver pronto para uso.
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync(this)

        clienteLocalizacao = LocationServices.getFusedLocationProviderClient(this)

        with(binding) {
            layoutLateral.visibility = View.GONE // Definir como invisível

            btnSair.setOnClickListener {
                autenticador.signOut()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }

            btnInflar.setOnClickListener {
                // Exibir os botões só quando o usuário clicar neles
                // Se estiver visível, esconde o botão; se estiver invisível, ativa o botão.
                if (layoutLateral.visibility == View.VISIBLE) {
                    layoutLateral.visibility = View.GONE
                } else {
                    layoutLateral.visibility = View.VISIBLE
                }
            }

            btnCalculoCombustivel.setOnClickListener {
                startActivity(Intent(applicationContext, CalculoCombustivelActivity::class.java))
            }

            // Olhar aqui, para fechar o item quando clicar em outro canto da tela.
            root.setOnClickListener {
                layoutLateral.visibility = View.GONE
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapa = googleMap

        // Solicitar permissão para acessar a localização do dispositivo
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO
            )
        } else {
            // Se a permissão já foi concedida, obter a localização atual
            mapa.isMyLocationEnabled =
                true // Pega a localização do aplicativo e tambem ativa o botao de "mira" para ir para seu local.
            obterLocalizacaoDispositivo()
        }


        // Exemplo de como adicionar marcador em Sydney e mover a camera pra la
        val sydney = LatLng(-34.0, 151.0)
        mapa.addMarker(MarkerOptions().position(sydney).title("Marcador em Sydney"))
        mapa.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Adicionar tema
        Temas.temaEscuro(this, googleMap)
    }

    // Método para obter a localização do dispositivo
    private fun obterLocalizacaoDispositivo() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        clienteLocalizacao.lastLocation.addOnSuccessListener { localizacao: Location? ->
            localizacao?.let {
                val latLngAtual = LatLng(localizacao.latitude, localizacao.longitude)
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAtual, 15f))
            }
        }
    }

    // Método para lidar com a resposta da solicitação de permissões
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CODIGO_SOLICITACAO_PERMISSAO_LOCALIZACAO && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            mapa.isMyLocationEnabled = true
            obterLocalizacaoDispositivo()
        }
    }
}
