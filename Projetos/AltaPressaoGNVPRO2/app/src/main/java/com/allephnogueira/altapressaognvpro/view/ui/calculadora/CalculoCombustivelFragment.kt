package com.allephnogueira.altapressaognvpro.view.ui.calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.allephnogueira.altapressaognvpro.databinding.FragmentCalculoCombustivelBinding


class CalculoCombustivelFragment : Fragment() {

    private lateinit var binding : FragmentCalculoCombustivelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCalculoCombustivelBinding.inflate(inflater, container, false )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Texto no resultado

        binding.btnCalcular.setOnClickListener{

            var campoResultado = binding.textResultado

            // Pega o texto, converterte para String depois Converte pra Double

            val precoAlcool = binding.textEtanol.text.toString().toDoubleOrNull()
            val precoGasolina = binding.textGasolina.text.toString().toDoubleOrNull()

            // Verificando se os campos tem itens para verificar
            if (precoAlcool != null && precoGasolina != null) {
                val resultado = if((precoAlcool / precoGasolina) < 0.7){ // Formula para verificar se alcool é mais barato
                    "Abasteça com Alcool"
                }else {
                    "Abasteça com Gasolina"
                }
                campoResultado.text = resultado
            }else {
                campoResultado.text = "Preencha os campos corretamente!"
            }

        }



    }


}