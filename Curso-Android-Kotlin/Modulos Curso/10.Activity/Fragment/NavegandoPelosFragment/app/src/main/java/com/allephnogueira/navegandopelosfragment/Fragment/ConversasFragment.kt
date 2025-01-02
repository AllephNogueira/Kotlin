package com.allephnogueira.navegandopelosfragment.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.allephnogueira.navegandopelosfragment.R

class ConversasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val minhaView = inflater.inflate(
            R.layout.fragment_conversas,
            container,
            false
        )

        minhaView.findViewById<TextView>(R.id.textView).text = "Abri o fragment 1"

        return minhaView
    }
}