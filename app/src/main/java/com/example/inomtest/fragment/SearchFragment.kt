package com.example.inomtest.fragment

import android.app.SearchManager
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.inomtest.*
import com.example.inomtest.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class SearchFragment : AppCompatActivity(), SearchView.OnQueryTextListener, OnDeleteListener {
    private lateinit var binding: FragmentSearchBinding
    lateinit var navController: NavController
    private lateinit var searchAdapter: RecentSearchAdapter
    private lateinit var db : RecentSearchDatabase
    private lateinit var searchDAO: RecentSearchDAO
    val recentWordList = mutableListOf<RecentSearchEntity>()
    //val recentWordList:List<RecentSearchEntity> = listOf<RecentSearchEntity>()
    private lateinit var searchViewEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //binding.RecentWordList.layoutManager = GridLayoutManager(this,2)
        //db= RecentSearchDatabase.getInstance(this)!!
        db = Room.databaseBuilder(this,RecentSearchDatabase::class.java,"room_db")
            .build()
        searchDAO = db.recentSearchDAO()
        // recentWordList.addAll(db.recentSearchDAO().getAll())//갱신
        searchAdapter = RecentSearchAdapter(this,recentWordList, this)

        refreshAdapter()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.apply {
            this.queryHint="검색어를 입력해주세요."
            this.setOnQueryTextListener(this@SearchFragment)
            //searchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text) //=>NPE, api31부터 막혔다고 함
            //searchViewEditText.setText("")
        }
        //searchViewEditText.apply { }

        onQueryTextChange(newText = null)
        with(binding) {
            RecentWordList.adapter = searchAdapter
            RecentWordList.layoutManager = GridLayoutManager(this@SearchFragment, 2)

            searchBtn.setOnClickListener {
                val content = binding.testEdit.text.toString()
                if (content.isNotEmpty()) {
                    val recentWord1 = RecentSearchEntity(null, content)
                    insertWord(recentWord1)
                }
            }
        }


    }
    //room 데이터 수정사항 업데이트
    fun refreshAdapter(){
        CoroutineScope(Dispatchers.IO).launch {
            recentWordList.clear()
            recentWordList.addAll(searchDAO.getAll())
            withContext(Dispatchers.Main){
                searchAdapter.notifyDataSetChanged()
            }
        }
    }
    //room 데이터 추가
    fun insertWord(searchWord:RecentSearchEntity){
        CoroutineScope(Dispatchers.IO).launch {
            searchDAO.insert(searchWord)
            refreshAdapter()
        }
    }
    //리사이클러뷰 삭제 _ 어댑터 파일과 연결
    override fun onDeleteListener(recentWord: RecentSearchEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            searchDAO.delete(recentWord)
            refreshAdapter()
        }
    }

//    fun setRecyclerviewRecent(recentWordList: List<RecentSearchEntity>){
//        binding.RecentWordList.adapter = RecentSearchAdapter(this,recentWordList,this)
//    }

    //서치뷰 검색어 입력 이벤트
    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.d(TAG, "SearchActivity - onQueryTextSubmit() called / query: $query")

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(TAG, "SearchActivity - onQueryTextChange() called / newText: $newText")

        return true
    }

}