package com.example.inomtest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.inomtest.R
import com.example.inomtest.databinding.FragmentSignupMainBinding
import com.example.inomtest.databinding.FragmentSignupPwBinding


class SignupPWFragment : Fragment() {

    private var _binding: FragmentSignupPwBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupPwBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupPWBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_signupPWFragment_to_signupNameFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignupPWFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}