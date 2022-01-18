package com.example.inomtest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.inomtest.R
import com.example.inomtest.databinding.FragmentSignupFinishBinding
import com.example.inomtest.databinding.FragmentSignupIdBinding
import com.example.inomtest.databinding.FragmentSignupNameBinding


class SignupFinishFragment : Fragment() {
    private var _binding: FragmentSignupFinishBinding? = null
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
        _binding = FragmentSignupFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupFinishBtn.setOnClickListener{
            Log.d("buttonTest", "버튼은 잘 눌림!!!!!!!")
            it.findNavController().navigate(R.id.action_signupFinishFragment_to_fragment_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupFinishFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}