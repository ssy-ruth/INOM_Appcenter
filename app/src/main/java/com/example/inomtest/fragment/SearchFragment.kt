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


//@SuppressLint("StaticFieldLeak")
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
        searchAdapter = RecentSearchAdapter(recentWordList)

        refreshAdapter()

        onQueryTextChange(newText = null)
        with(binding) {
            RecentWordList.adapter = searchAdapter
            RecentWordList.layoutManager = GridLayoutManager(this@SearchFragment, 2)

            searchBtn.setOnClickListener {
                val content = searchViewEditText.text.toString()
                if (content.isNotEmpty()) {
                    val recentWord1 = RecentSearchEntity(null, content)
                    insertWord(recentWord1)
                }
            }
        }


    }
    fun refreshAdapter(){
        CoroutineScope(Dispatchers.IO).launch {
            recentWordList.clear()
            recentWordList.addAll(searchDAO.getAll())
            withContext(Dispatchers.Main){
                searchAdapter.notifyDataSetChanged()
            }
        }
    }
    fun insertWord(searchWord:RecentSearchEntity){
        CoroutineScope(Dispatchers.IO).launch {
            searchDAO.insert(searchWord)
            refreshAdapter()
        }
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
        Log.d(TAG, "PhotoCollectionActivity - onQueryTextSubmit() called / query: $query")

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.d(TAG, "PhotoCollectionActivity - onQueryTextChange() called / newText: $newText")
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.apply {
            this.queryHint="검색어를 입력해주세요."
            this.setOnQueryTextListener(this@SearchFragment)
            //searchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text) //=>NPE, api31부터 막혔다고 함
            //searchViewEditText.setText("")
        }
        searchViewEditText.apply {
        }
        return true
    }

    override fun onDeleteListener(recentWord: RecentSearchEntity) {
        //deleteRecentWord(recentWord)
    }
}