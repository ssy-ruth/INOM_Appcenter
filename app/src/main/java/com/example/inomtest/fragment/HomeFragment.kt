package com.example.inomtest.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.inomtest.R
import com.example.inomtest.RecyclerItemAdapter
import com.example.inomtest.dataClass.ItemData
import com.example.inomtest.databinding.FragmentHomeBinding
import com.example.inomtest.databinding.FragmentLoginBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = ArrayList<ItemData>()
        list.add(ItemData(ContextCompat.getDrawable(requireContext(), R.drawable.image_sample)!!, "제목1", "가격1"))
        list.add(ItemData(ContextCompat.getDrawable(requireContext(), R.drawable.image_sample)!!, "제목2", "가격2"))
        list.add(ItemData(ContextCompat.getDrawable(requireContext(), R.drawable.image_sample)!!, "제목3", "가격3"))

        val adapter = RecyclerItemAdapter(list)
        binding.rvItemList.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}