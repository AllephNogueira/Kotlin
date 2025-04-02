package com.allephnogueira.whatsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.allephnogueira.whatsapp.fragmentos.ContatosFragment
import com.allephnogueira.whatsapp.fragmentos.ConversasFragment

class ViewPagerAdapter(
    private val abas : List<String>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){


    override fun getItemCount(): Int {
        // Aqui vamos passar a quantidade de abas que queremos criar.
        return abas.size //         val abas = listOf(0 -> "Conversas", 1 -> "Contatos")
    }

    override fun createFragment(position: Int): Fragment {
        // Aqui vamos pegar a posicação da lista
        // Imagina que 0 seja conversas, então vamos abrir o fragmento de conversas
        // Imagina que 1 seja contato, então vamos abrir o fragmento de contatos

        // Aqui ele precisa retornar um fragmento.
        // Agora vamos criar o fragmentos.
        when (position ) {
            1 -> return ContatosFragment()
        }

        // Lembrar que sempre aqui dentro precisamos retornar um Fragment
        // Então para isso vamos retornar um fragment padrao.

        return ConversasFragment()
    }


}