package com.example.pushnotification.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pusnothification.R
import com.example.pusnothification.databinding.FirstScreenBinding


class FirstScreen : Fragment() {

    private lateinit var binding : FirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.first_screen,container,false)

        binding.apply {


        }

        return binding.root
    }

}