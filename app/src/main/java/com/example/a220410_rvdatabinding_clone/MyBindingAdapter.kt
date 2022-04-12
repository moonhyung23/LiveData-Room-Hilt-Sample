package com.example.a220410_rvdatabinding_clone

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object MyBindingAdapter {
    @BindingAdapter("items")
    @JvmStatic
    //리사이클러뷰 세팅
    fun setItems(recyclerView: RecyclerView, items: ArrayList<User>) {
        if (recyclerView.adapter != null) {
            val adapter = MyAdapter()
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }

        val myAdapter = recyclerView.adapter as MyAdapter
        myAdapter.List_user = items
        myAdapter.notifyDataSetChanged()
    }
}