package com.example.inomtest.fragment

import android.os.Bundle
import android.util.Log
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

    val bundle = Bundle()
    var inuID : String = ""
    var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inuID = arguments?.getString("inuID").toString()
        Log.d("아이디", inuID)
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
            password = binding.signupPWEdit.text.toString()
            bundle.putString("inuID", inuID)
            bundle.putString("password", password)

            it.findNavController().navigate(R.id.action_signupPWFragment_to_signupNameFragment, bundle)
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