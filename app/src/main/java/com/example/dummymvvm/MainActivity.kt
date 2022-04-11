package com.example.dummymvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.dummymvvm.adapter.EntryAdapter
import com.example.dummymvvm.databinding.ActivityMainBinding
import com.example.dummymvvm.model.EntriesItem
import com.example.dummymvvm.network.ApiService
import com.example.dummymvvm.network.Resource
import com.example.dummymvvm.network.RetrofitService
import com.example.dummymvvm.repository.EntryRepository
import com.example.dummymvvm.utils.MyFactory
import com.example.dummymvvm.viewModel.EntriesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EntriesViewModel
    private lateinit var binding: ActivityMainBinding
    private var list:MutableList<EntriesItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MyFactory(EntryRepository(RetrofitService().buildApi().create(ApiService::class.java)))
        )[EntriesViewModel::class.java]

        viewModel.entries()

        viewModel.entriesResponse.observe(this) {
            list.clear()
            when(it){
                is Resource.Success ->{
                    if (it.value.count == 1420){
                        for (i in it.value.entries!!.indices){
                            list.add(it.value.entries[i]!!)
                        }
                        binding.entryRecycler.adapter = EntryAdapter(list)
                        binding.progressBar.visibility = View.GONE
//                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}