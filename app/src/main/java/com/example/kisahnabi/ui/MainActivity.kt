package com.example.kisahnabi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kisahnabi.data.KisahResponse
import com.example.kisahnabi.databinding.ActivityMainBinding
import com.example.kisahnabi.ui.detail.DetailActivity
import com.example.kisahnabi.utils.OnItemClickCallback

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.apply {
            getData()
            kisahResponse.observe(this@MainActivity) { showData(it) }
            isLoading.observe(this@MainActivity) { showLoading(it) }
            isError.observe(this@MainActivity) { showError(it) }
        }
    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter

            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(item: KisahResponse) {
                    startActivity(
                        Intent(
                            applicationContext,
                            DetailActivity::class.java
                        ).putExtra(DetailActivity.EXTRA_DATA, item)
                    )
                }
            })
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.apply {
                progressMain.visibility = View.VISIBLE
                recyclerMain.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressMain.visibility = View.INVISIBLE
                recyclerMain.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("MainActivity", "showError: $error")
    }
}