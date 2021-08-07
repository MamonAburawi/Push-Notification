package com.example.pushnotification.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pusnothification.R
import com.example.pusnothification.databinding.FirstScreenBinding
import com.example.pusnothification.databinding.SecondScreenBinding


class SecondScreen : Fragment() {

    private lateinit var binding : SecondScreenBinding

    companion object{
        const val DESTINATION_TO_SECOND_SCREEN = R.id.secondScreen
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.second_screen,container,false)

        binding.apply {


        }

        return binding.root
    }

}