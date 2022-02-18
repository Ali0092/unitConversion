package com.example.unitconversion.screens.loginScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unitconversion.R
import com.example.unitconversion.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLoginBinding.inflate(inflater,container,false)

        binding.loginBtn.setOnClickListener {

            //the check is not working yet...
            if(binding.nameEt.text.isEmpty() && binding.passEt.text.isEmpty())
                Toast.makeText(context,"Name and password must not be null", Toast.LENGTH_LONG).show()
            else
                this.findNavController().navigate(R.id.action_loginFragment2_to_menuFragment2)
        }

        return binding.root
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.aboutFragment)
            findNavController().navigate(R.id.action_loginFragment2_to_aboutFragment)
        return true
    }
}