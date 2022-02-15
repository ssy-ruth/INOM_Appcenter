package com.example.inomtest


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inomtest.dataClass.ItemData
import com.example.inomtest.databinding.ItemLoadingBinding
import com.example.inomtest.databinding.ItemViewBinding


class RecyclerItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val items = ArrayList<ItemData>()

    // 아이템뷰에 게시물이 들어가는 경우
    inner class ItemsViewHolder(private val binding: ItemViewBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemData) {
            binding.itemTextViewTitle.text = item.title
            binding.itemTextViewPrice.text = item.price.toString()
        }
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemViewType(position: Int): Int {
        // 게시물과 프로그레스바 아이템뷰를 구분할 기준이 필요함.
        return when (items[position].title) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
                ItemsViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemsViewHolder){
            holder.bind(items[position])
        }else{

        }
    }

    fun setList(items: MutableList<ItemData>) {
        items.addAll(items)
        items.add(ItemData(0, "제목 초기화", "내용 초기화", 0,
            0, "상태 초기화", "", "")) // 프로그레스바 넣을 자리
    }

    fun deleteLoading() {
        items.removeAt(items.lastIndex) // 로딩이 완료되면 프로그레스바를 지움
    }


}