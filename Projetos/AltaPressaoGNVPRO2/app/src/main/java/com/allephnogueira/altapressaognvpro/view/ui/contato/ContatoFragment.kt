package com.allephnogueira.altapressaognvpro.view.ui.contato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.allephnogueira.altapressaognvpro.databinding.FragmentContatoBinding


class ContatoFragment : Fragment() {

    private lateinit var binding: FragmentContatoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentContatoBinding.inflate(inflater, container, false)
        return  binding.root
    }

}