package com.example.inomtest


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.inomtest.dataClass.ItemData
import com.example.inomtest.databinding.FragmentHomeBinding.bind
import com.example.inomtest.databinding.ItemViewBinding


class RecyclerItemAdapter(
    private val items: ArrayList<ItemData>) :
    RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerItemAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.itemTitle.text = this.item_title
                binding.itemPrice.text = this.item_price
            }
        }
    }


}