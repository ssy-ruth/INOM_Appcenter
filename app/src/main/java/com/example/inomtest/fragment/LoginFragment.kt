package com.example.inomtest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.inomtest.R
import com.example.inomtest.dataClass.LoginData
import com.example.inomtest.databinding.FragmentLoginBinding
import com.example.inomtest.databinding.FragmentSignupFinishBinding
import com.example.inomtest.network.InomApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController

    var inuID : String = ""
    var password : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inuID = binding.loginIDEdit.text.toString()
        password = binding.loginIDEdit.text.toString()

        binding.loginBtn.setOnClickListener{
            login(inuID, password)

            it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.loginToSignup.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_signupMainFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun login(inuID: String, password: String) {
        var LoginData = LoginData()
        LoginData.inuID = inuID
        LoginData.password = password
        LoginData.pushToken = "pushToken"

        val call = InomApi.createApi().login("Bearer **", LoginData())

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("로그인결과1", "통신결과"+response.code().toString())
                }

                else {
                    Log.d("로그인결과2", "통신결과"+response.code().toString())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("로그인결과3", "통신결과: $t")
            }
        })
    }
}