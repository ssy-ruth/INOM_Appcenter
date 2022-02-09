package com.example.inomtest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.inomtest.R
import com.example.inomtest.databinding.FragmentSignupIdBinding
import androidx.navigation.findNavController


class SignupIDFragment : Fragment() {
    private var _binding: FragmentSignupIdBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController

    val bundle = Bundle()
    var inuID : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupIdBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupIDBtn.setOnClickListener{
            inuID = binding.signupIDEdit.text.toString()
            bundle.putString("inuID", inuID)

            it.findNavController().navigate(R.id.action_signupIDFragment_to_signupPWFragment, bundle)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignupIDFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}