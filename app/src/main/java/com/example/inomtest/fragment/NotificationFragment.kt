package com.example.inomtest.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inomtest.NotificationAdapter
import com.example.inomtest.R
import com.example.inomtest.dataClass.DataNotification
import com.example.inomtest.dataClass.NotificationData
import com.example.inomtest.databinding.FragmentNotificationBinding
import com.example.inomtest.network.App
import com.example.inomtest.network.InomApi
import com.example.inomtest.network.InomApiService
import com.example.inomtest.network.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private val notifiAdapter = NotificationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //알림 api 호출
        RetrofitManager.instance.notifiApi(completion = {
                responseState, responseBody ->

            when(responseState){
                RetrofitManager.RESPONSE_STATE.OKAY->{
                    Log.d(ContentValues.TAG,"api 호출 성공 : $responseBody")
                    notifiAdapter
                }
                RetrofitManager.RESPONSE_STATE.FAIL->{
                    Log.d(ContentValues.TAG,"api 호출 실패 : $responseBody")
                }
            }
        })
        //리싸이클러뷰 설정
        binding.notifiRecycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            adapter = notifiAdapter
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))//선그어주기
        }
        binding.backBtn.setOnClickListener{
            it.findNavController().navigate(R.id.action_notificationFragment_to_homeFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}