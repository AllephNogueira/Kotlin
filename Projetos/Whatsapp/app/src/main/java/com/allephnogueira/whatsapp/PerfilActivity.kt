package com.allephnogueira.whatsapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.whatsapp.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPerfilBinding.inflate( layoutInflater ) }

    private var temPermissaoCamera = false
    private var temPermissaoGaleria = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciarToolbar()
        soliciarPermissoes()

    }

    private fun soliciarPermissoes() {
        /** Devemos inciar a permissao de galeria, logo quando o usuario clicar em perfil */

        /* 1 Verificar se o usuario já tem a permissao.
        Adicionamos 2 atributos para isso.
            private var temPermissaoCamera = false
            private var temPermissaoGaleria = false

          Aqui vamos verificar, se o usuario tem a permisao ou não
          O == verifica se a permissao é igual a true.
         */
        temPermissaoCamera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        temPermissaoGaleria = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED

        /* Sabemos que na primeira execução o usuario não vai ter essas permissoes
        Então vamos criar uma lista de permissoes negadas.
         */

        val listaPermissoesNegadas = mutableListOf<String>()
        if (!temPermissaoCamera) {
            // Não tem permissao da camera? então adicionamos ela na lista
            listaPermissoesNegadas.add(Manifest.permission.CAMERA)
        }
        if (!temPermissaoGaleria) {
            // Não tem permissao da camera? então adicionamos ela na lista
            listaPermissoesNegadas.add(Manifest.permission.READ_MEDIA_IMAGES)
        }

        if (listaPermissoesNegadas.isNotEmpty()) {
            // Sempre quando uma permissao é negada, adicionamos essa permissao dentro dessa lista
            // Agora se a lista NÃO ESTIVER VAZIA é sinal que tem permissao negada la dentro
            // AI sim vamos solicitar essas permissoes negadas novamente.


            // SOLICITAR MULTIPLAS PERMISSOES
            val gerenciadorDePermissoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ){ permissoes ->
                // Aqui vai ser o seguinte, ?: se existir a permissao e for verdadeira, vamos configurar o valor aqui
                // Se for falso, adicionamos o falso que ja esta configurado dentro de temPermissaoCamera
                temPermissaoCamera = permissoes[Manifest.permission.CAMERA] ?: temPermissaoCamera
                temPermissaoGaleria = permissoes[Manifest.permission.READ_MEDIA_IMAGES] ?: temPermissaoCamera
            }
            gerenciadorDePermissoes.launch(listaPermissoesNegadas.toTypedArray())
        }



    }

    private fun iniciarToolbar() {
        val toolbar = binding.includeToolbarPerfil.tbPrincipal
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Perfil"

            setDisplayHomeAsUpEnabled(true)
        }
    }


}