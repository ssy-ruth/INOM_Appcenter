package com.example.inomtest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inomtest.RecentSearchDatabase
import com.example.inomtest.RecentSearchEntity
import com.example.inomtest.databinding.ActivityMainBinding
import com.example.inomtest.databinding.FragmentSearchBinding


class SearchFragment : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    lateinit var db : RecentSearchDatabase
    var recentWordList:List<RecentSearchEntity> = listOf<RecentSearchEntity>()
    private lateinit var searchView: SearchView
    private lateinit var searchViewEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
        db= RecentSearchDatabase.getInstance(this)!!
        binding.searchView.apply {
            this.queryHint="검색어를 입력해주세요."
            this.setOnQueryTextListener(this@SearchFragment)
            searchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)
        }
        binding.searchBtn.setOnClickListener {
            var recentWord = RecentSearchEntity(null, searchViewEditText.text.toString())
            insertRecentWord()
        }
    }

 /*   override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
        binding.RecentWordList.layoutManager = GridLayoutManager(context,2)
    }*/



    fun insertRecentWord(){

    }
    fun getAllRecentWord(){

    }
    fun deleteRecentWord(){

    }
    fun setRecyclerviewRecent(){

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}