package com.example.inomtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inomtest.databinding.ItemSearchRecentBinding

class RecentSearchAdapter(val context: Context,
                          var list: List<RecentSearchEntity>
                        ,var onDeleteListener: OnDeleteListener
                          ):
    RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {
    private var _binding: ItemSearchRecentBinding? = null
    private val binding get() = _binding!!


    override fun getItemCount(): Int {
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_recent,parent,false)

        return RecentSearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val recentWord : RecentSearchEntity = list[position]
        holder.recentWord.text = recentWord.recentWord
        holder.recentWordDelete.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                //onDeleteListener.onDeleteListener(recentWord)
            }
        })
    }

    inner class RecentSearchViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val recentWord = binding.textviewRecentWord
        val recentWordDelete = binding.recentWordDelete
    }


}