package com.example.dummymvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummymvvm.model.EntriesItem
import com.example.dummymvvm.R
import com.example.dummymvvm.databinding.CustomEntryListBinding

class EntryAdapter(val list:List<EntriesItem>): RecyclerView.Adapter<EntryAdapter.EntryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryHolder {
       return EntryHolder(CustomEntryListBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.custom_entry_list,parent,false)))
    }

    override fun onBindViewHolder(holder: EntryHolder, position: Int) {
       with(holder){
           binding.apiName.text = list[position].aPI
       }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    inner class EntryHolder(var binding: CustomEntryListBinding): RecyclerView.ViewHolder(binding.root)
}