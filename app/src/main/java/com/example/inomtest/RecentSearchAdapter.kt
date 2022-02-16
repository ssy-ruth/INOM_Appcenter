package com.example.inomtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inomtest.dataClass.room.OnDeleteListener
import com.example.inomtest.dataClass.room.RecentSearchEntity
import com.example.inomtest.databinding.ItemSearchRecentBinding

class RecentSearchAdapter(val context: Context,
                          var list: List<RecentSearchEntity>
                        ,var onDeleteListener: OnDeleteListener
                          ):
    RecyclerView.Adapter<RecentSearchAdapter.Holder>() {

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding= ItemSearchRecentBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recentWord : RecentSearchEntity = list[position]
//        holder.recentWord.text = recentWord.recentWord
        holder.setWord(list.get(position))
        holder.deleteHistory.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                onDeleteListener.onDeleteListener(recentWord)
            }
        })
    }

//    inner class RecentSearchViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
//        val recentWord = binding.textviewRecentWord
//        val recentWordDelete = binding.recentWordDelete
//    }
    class Holder(val binding: ItemSearchRecentBinding):RecyclerView.ViewHolder(binding.root){
        fun setWord(recentWord: RecentSearchEntity){
            binding.textviewRecentWord.text = recentWord.recentWord
        }
    val deleteHistory = binding.recentWordDelete
    }


}