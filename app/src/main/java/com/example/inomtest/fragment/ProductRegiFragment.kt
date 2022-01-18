package com.example.inomtest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.inomtest.R


class ProductRegiFragment : Fragment() {
    private lateinit var spinner_category: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        spinner_category.findViewById<Spinner>(R.id.regi_category)

        setupSpinnerCategory()
    }

    private fun setupSpinnerCategory() {
        val items = resources.getStringArray(R.array.spineer_category)
        val adapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        spinner_category.adapter = adapter
    }

    private fun setupSpinnerHandler() {
        spinner_category.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, positin: Int, id: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_product_regi, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductRegiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductRegiFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}