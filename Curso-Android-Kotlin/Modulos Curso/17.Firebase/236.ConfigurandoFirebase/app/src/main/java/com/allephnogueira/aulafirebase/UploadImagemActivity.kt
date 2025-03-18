package com.allephnogueira.aulafirebase

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.allephnogueira.aulafirebase.databinding.ActivityUploadImagemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.util.UUID

class UploadImagemActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUploadImagemBinding.inflate(layoutInflater) }

    /*1 Primeiro vamos criar esse atributo */
    private var uriImagemSelecionada: Uri? = null


    private var bitmapImagemSelecionada: Bitmap? = null


    private val abrirGaleria = registerForActivityResult(
        /** Oque esse codigo faz? ele espera abrir uma acitivty de resultado
         * Tinhamos tambem esse outro metodo, mas foi depreciado.
         * Tradução do registerForActivityResult
         * Registrar para um resultado de activity
         *
         * 2 Aqui dentro do metodo precisamos passar oque vai retornar como resultado
         * GetContent = usuario vai pegar um documento || eu vou pegar conteudos.
         *
         *
         * 3 uri -> esse é o endereço que esta nossa foto, é onde esta nossa foto na galeria.
         * Quando abrimos uma imagem, ela vai retornar o local onde esta essa imagem
         * ex: c://computador//alleph//imagem//fotopraia.png
         *
         * 4 Devemos fazer um teste na URI porque ela pode ser nula também
         * Se encontrar a imagem podemos usar ela, porque é bem simples.
         */
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            /*2 Aqui vamos passar esse atributo
            * Agora dentro de imagemSelecionada ja vamos ter o local de onde esta essa imagem
            * Agora vamos fazer o upload dessa imagem */
            uriImagemSelecionada = uri

            binding.imageSelecionada.setImageURI(uri)
            Toast.makeText(this, "Imagem selecionada.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nenhuma imagem selecionada.", Toast.LENGTH_SHORT).show()
        }
    }


    private val abrirCamera = registerForActivityResult( // Registrar um resultado de uma activity
        ActivityResultContracts.StartActivityForResult()
    ) { resultadoActivity ->
        /*// resultCode = codigo de resultado.
        // Aqui só vai entrar no IF se o resultado der certo.
        if (resultadoActivity.resultCode == RESULT_OK) {

        }else {

        }
        Não vamos fazer dessa forma, vamos fazer de uma forma mais curta.
        */

        /* extras e quando pegamos dados de uma outra activity
        Como aqui estamos abrindo uma activity(camera)
        E pegando seu resultado
        aqui estamos usando o extras para poder captpurar seus dados.

        data = recuperar imagem da camera

        Bitmap = podemos trocar o formato da imagem ex: PNG para JPG

        esse getParcelable ele so funciona para versoes tiramisu então vamos verificar isso

         */
        bitmapImagemSelecionada = if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            resultadoActivity
                .data?. // Onde esta o arquivo
                extras?. // Vamos passar o arquivo de outra activity
                getParcelable("data", Bitmap::class.java) // Data porque vamos usar a camera // Bitmap para converter o tipo da imagem.
        }else {
            resultadoActivity
                .data?. // Onde esta o arquivo
                extras?. // Vamos passar o arquivo de outra activity
                getParcelable("data") // Data porque vamos usar a camera
        }

        binding.imageSelecionada.setImageBitmap(bitmapImagemSelecionada)
    }


    //Armazenamento
    private val armazenamento by lazy {
        FirebaseStorage.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGaleria.setOnClickListener {
            abrirGaleria.launch("image/*") // Mime Type


//             launch = executar - todo aquele bloco de notas que fizemos la em cima.
//             * Mime Type = qual tipo de arquivo queremos exibir.  (pegue a lista no google)
//             * Voce pode pesquisar no google
//             * Aqui no input ele espera receber uma string com o tipo de dados.
//             *
//             * Aqui por exemplo vamos trabalhar com imagem e todos os tipos de imagem
//             * Então vai ficar image/**
//             * Se quiser trabalhar com um tipo de imagem ficaria
//             * image.png
//             *
//             * Se quiser trabalhar com audio ficaria
//             * audio/mpeg    - audio/vorbis   - audio/* < para todos os tipos de video
//


        }

        binding.btnCamera.setOnClickListener {
            /* Aqui como usamos o StartActivityForResult precisamos passar uma intent
            * Ja na galeria informamos o tipo de dados que queremos pegar.
            *
            * MediaStore = vai acessar varios recursos que podemos usar, nesse caso aqui vamos acessar uma ação e essa ação vai ser capturar imagem...*/
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            abrirCamera.launch(intent)

        }


        binding.btnUpload.setOnClickListener {
            //uploadGaleria()
            uploadCamera()
        }

        binding.btnRecuperar.setOnClickListener {
            recuperarImagemFirebase()
        }
    }



    private fun uploadCamera() {

        /*
        Se quiser entender melhor o comentario do codigo, veja uploadGaleria
         */
        val idUsuario = FirebaseAuth.getInstance().currentUser?.uid

        if (idUsuario != null) {

            if (bitmapImagemSelecionada != null) {


                /* Imagem selecionada é do tipo bitmap, mas precisamos usar uma imagem do tipo bitArray
                e para isso vamos precisar converter ela, no exemplo vamos converter para JPEG

                Metodo compress: 3 parametros
                    Format - qualidade de imagem (0 ate 100) - out put stream
                        QUALIDADE IMAGEM: Atenção o usuario pode subir uma imagem muito grande, e com isso voce ocupa muito espaço, ideal é voce tentar subir com pelo menos 50%
                Esse metodo compress = comprimir, mas na verdade voce transformar de bitmap para JPG/PNG...
                 */

                val outputStream = ByteArrayOutputStream()
                bitmapImagemSelecionada?.compress(
                    Bitmap.CompressFormat.JPEG, // Vamos converter / comprimir para JPEG
                    100, // Vamos deixar a qualidade em 100%
                    outputStream // Ele vai colocar essa imagem aqui dentro....
                )


                armazenamento.getReference("fotos")
                    .child(idUsuario)
                    .child("foto.jpg")
                    .putBytes(outputStream.toByteArray()) // é uma maneira de empacotar sua imagem e enviar. // Nesse objeto que vai ter a imagem. Vamos converter para toByteArray
                    .addOnSuccessListener { task ->
                        Toast.makeText(this, "Imagem enviada!", Toast.LENGTH_SHORT).show()
                        task.metadata?.reference?.downloadUrl?.

                        addOnSuccessListener { urlFirebase ->
                            uploadImagemFirestore(urlFirebase!!)
                            Toast.makeText(this, "$urlFirebase", Toast.LENGTH_SHORT).show()

                        }
                    }.addOnFailureListener { erro ->
                        Toast.makeText(this, "Falha ao enviar a imagem.", Toast.LENGTH_SHORT).show()
                    }
            }
        }


    }

    private fun recuperarImagemFirebase() {
        val idUsuarioLogado = FirebaseAuth.getInstance().currentUser?.uid
        if (idUsuarioLogado != null ) {

            armazenamento.getReference("fotos")
                .child(idUsuarioLogado)
                .child("foto.jpg") // Nesse caso vamos estar recuperando pelo proprio nome do arquivo.
                .downloadUrl // Aqui vai bixar a URL da imagem para a gente
                .addOnSuccessListener { urlFirebase -> // Se for sucesso - vai retornar a URL la do firebase
                    Toast.makeText(this, "URL recuperado: $urlFirebase", Toast.LENGTH_SHORT).show()


                    Picasso.get()
                        .load(urlFirebase)
                        .resize(200, 160)
                        .centerCrop()
                        .into(binding.imageRecuperada)


                }

        }
    }

    private fun uploadGaleria() {

        // Pegando o ID do usuario, mas ele precisa logar
        // E se voce precisar logar ele, voce pode inicar a principal activity de novo...
        val idUsuario = FirebaseAuth.getInstance().currentUser?.uid

        if (idUsuario != null) {
            // Primeiro devemos verificar se nossa URI = endereço da nossa imagem
            // Ela não pode ser nula.

            if (uriImagemSelecionada != null) {

                /*
                    fotos
                        viagens
                            + foto1.png
                            + foto2.png
                 */

                /* Esse modo e para ilustrar melhor
                armazenamento
                    .getReference("fotos") // Criar a pasta fotos. (Se existir ele usa, se nao ele cria)
                    .child("viagens") // == FILHO  - vamos definir um filho para a pasta
                    .child("foto1.pgn") // Agora vamos definir o nome do arquivo.
                    /*
                    Atenção se voce nao quiser usar a pasta viagens, voce vai ter apenas a pasta fotos e o arquivo da foto.
                    Voce não é obrigado a criar a pasta "viagens"
                     */

                 */

                val gerarNomeParaImagem = UUID.randomUUID().toString() // Agora vamos gerar um nome automatico para a imagem.

                armazenamento.getReference("fotos")
                    .child(idUsuario)
                    .child(gerarNomeParaImagem) // Agora ele vai gerar sempre um nome diferente para cada foto, se tiver nome igual ele fica substituindo...

                    .putFile(uriImagemSelecionada!!) //Aqui estamos enviando putFile = arquivo, mas poderiamos enviar de varias formas ex: putBytes
                    /** Atenção a uriImagemSelecionada é o caminho no celular do usuario
                     * Quando da sucesso a gente retorna com o taks a URL dentro do Storage
                     */
                    .addOnSuccessListener { task ->
                        Toast.makeText(this, "Imagem enviada!", Toast.LENGTH_SHORT).show()


                        /** Se for sucesso ao salvar a imagem, podemos salvar essa imagem também dentro do firestor
                         * Para futuramente a gente poder acessar essa imagem usando o picaso
                         * Dessa forma estamos salvando a imagem dentro do proprio ID do usuario.
                         */
                        task.metadata?.reference?.downloadUrl?.addOnSuccessListener { urlFirebase ->

                            uploadImagemFirestore(urlFirebase!!)
                            Toast.makeText(this, "$urlFirebase", Toast.LENGTH_SHORT).show()
                        }


                    }.addOnFailureListener { erro ->
                        Toast.makeText(this, "Falha ao enviar a imagem.", Toast.LENGTH_SHORT).show()
                    }
            }
        }


    }

    private fun uploadImagemFirestore(urlFirebase: Uri) {
        // 1 primeiro ID do usuario logado
        val idUsuarioLogado = FirebaseAuth.getInstance().currentUser?.uid
        if (idUsuarioLogado != null) {

            // Instancia do banco de dados
            val bancoDeDados = FirebaseFirestore.getInstance()

            // Pegando imagem do usuario

            val dados = mapOf(
                "url_imagem" to urlFirebase
            )

            bancoDeDados.collection("usuarios")
                .document(idUsuarioLogado)
                .update(dados)
                .addOnSuccessListener {
                    Toast.makeText(this, "Imagem carregada!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "ERRO: Não conseguimos guardar sua foto.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }
}