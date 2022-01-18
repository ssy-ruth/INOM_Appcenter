package com.example.inomtest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.inomtest.R
import com.example.inomtest.databinding.FragmentSignupIdBinding
import com.example.inomtest.databinding.FragmentSignupNameBinding

class SignupNameFragment : Fragment() {
    private var _binding: FragmentSignupNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupNameBtn.setOnClickListener{
            Log.d("buttonTest", "버튼은 잘 눌림!!!!!!!")
            it.findNavController().navigate(R.id.action_signupNameFragment_to_signupFinishFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupNameFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}