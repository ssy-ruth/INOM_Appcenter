package com.example.inomtest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inomtest.RecentSearchDatabase
import com.example.inomtest.RecentSearchEntity
import com.example.inomtest.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    lateinit var db : RecentSearchDatabase
    var recentWordList:List<RecentSearchEntity> = listOf<RecentSearchEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = RecentSearchDatabase.getInstance(this)!!
        binding.searchBtn.setOnClickListener {
            var recentWord = RecentSearchEntity(null,)
            insertRecentWord()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
        binding.RecentWordList.layoutManager = GridLayoutManager(context,2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun insertRecentWord(){

    }
    fun getAllRecentWord(){

    }
    fun deleteRecentWord(){

    }
    fun setRecyclerviewRecent(){

    }
}