package com.example.inomtest.fragment

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.inomtest.*
import com.example.inomtest.databinding.FragmentSearchBinding
import com.example.inomtest.databinding.ItemSearchRecentBinding


//@SuppressLint("StaticFieldLeak")
class SearchFragment : AppCompatActivity(), SearchView.OnQueryTextListener, OnDeleteListener {
    private lateinit var binding: FragmentSearchBinding
    //private val binding get() = _binding!!
    private var _binding1: ItemSearchRecentBinding? = null
    private val binding1 get() = _binding1!!
    lateinit var navController: NavController
    lateinit var searchAdapter: RecentSearchAdapter
    lateinit var db : RecentSearchDatabase
    lateinit var searchDAO: RecentSearchDAO
    val recentWordList = mutableListOf<RecentSearchEntity>()
    //val recentWordList:List<RecentSearchEntity> = listOf<RecentSearchEntity>()
    private lateinit var searchView: SearchView
    private lateinit var searchViewEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //binding.RecentWordList.layoutManager = GridLayoutManager(this,2)
        //db= RecentSearchDatabase.getInstance(this)!!
        db = Room.databaseBuilder(this,RecentSearchDatabase::class.java,"room_db")
            .allowMainThreadQueries()//공부할때만 사용,,,, 수정해야함
            .build()
        searchDAO = db.recentSearchDAO()
       // recentWordList.addAll(db.recentSearchDAO().getAll())//갱신
        searchAdapter = RecentSearchAdapter(recentWordList)
        refreshAdapter()

        with(binding){
            RecentWordList.adapter = searchAdapter
            RecentWordList.layoutManager = GridLayoutManager(this@SearchFragment,2)

            searchBtn.setOnClickListener{
                val content = searchViewEditText.text.toString()
                if (content.isNotEmpty()){
                    val recentWord1 = RecentSearchEntity(null,content)
                    searchDAO.insert(recentWord1)

                    refreshAdapter()
                }
            }
        }
        binding.searchView.apply {
            this.queryHint="검색어를 입력해주세요."
            this.setOnQueryTextListener(this@SearchFragment)
            searchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)
        }
        binding.searchBtn.setOnClickListener {//room으로 하게 되어서 검색 버튼을 누르면 room에 검색어가 저장되는 식으로 하였지만 searchview에 최근 검색어 저장기능이 있음.
                                            //이게 왜 고민이냐면 searchview 위젯은 키보드에 있는 검색 버튼에 접근이 가능한데 room은 오직 화면의 버튼만 가능하다는 점이 다름..
                                            //키보드의 검색 버튼을 없애던지(searchview말고 edittext를 사용하거나 searchview에 없애는 명령어가 있다면 사용)
                                            //searchcview에서 제공되는 키보드버튼을 클릭리스너 식으로 연결하여 room과 연결시키던지....
            val recentWord = RecentSearchEntity(null, searchViewEditText.text.toString())
            //insertRecentWord(recentWord)//binding1.textviewRecentWord
        }
    }
    fun refreshAdapter(){
        recentWordList.clear()
        recentWordList.addAll(searchDAO.getAll())
        searchAdapter.notifyDataSetChanged()
    }



/*
    fun insertRecentWord(recentWord: RecentSearchEntity) {

        val insertTask = object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                db.recentSearchDAO().insert(recentWord)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllRecentWord()
            }
        }

    }*/
    /*
    fun getAllRecentWord(){

        val getTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                recentWordList = db.recentSearchDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerviewRecent(recentWordList)
            }
        }
        getTask.execute()
    }*/
    /*
    fun deleteRecentWord(recentWord: RecentSearchEntity){
        val deleteTask = object : AsyncTask<Unit,Unit,Unit,>(){
            override fun doInBackground(vararg params: Unit?) {
                db.recentSearchDAO().delete(recentWord)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllRecentWord()
            }
        }
        deleteTask.execute()

    }*/
    fun setRecyclerviewRecent(recentWordList: List<RecentSearchEntity>){

        binding.RecentWordList.adapter = RecentSearchAdapter(recentWordList)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onDeleteListener(recentWord: RecentSearchEntity) {
        //deleteRecentWord(recentWord)
    }
}