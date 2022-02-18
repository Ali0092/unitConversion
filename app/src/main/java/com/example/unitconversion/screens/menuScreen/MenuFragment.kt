package com.example.unitconversion.screens.menuScreen

import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.unitconversion.R
import com.example.unitconversion.databinding.FragmentMenuBinding
import com.example.unitconversion.screens.loginScreen.LoginFragment


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentMenuBinding.inflate(inflater,container,false)

       binding.timeConvertorBtn.setOnClickListener {
         Navigation("time")
       }
       binding.lengthConvertorBtn.setOnClickListener {
            Navigation("length")
       }
        binding.witghtConvertorBtn.setOnClickListener {
            Navigation("weight")
        }
        binding.tempConvertorBtn.setOnClickListener {
            Navigation("temp")
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.aboutFragment)
            findNavController().navigate(R.id.action_menuFragment2_to_aboutFragment)
        return true
    }

private fun Navigation(name:String){
    this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToConvertorFragment(name))
}
}