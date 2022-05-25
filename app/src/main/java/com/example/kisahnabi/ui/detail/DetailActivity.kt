package com.example.kisahnabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kisahnabi.R
import com.example.kisahnabi.data.KisahResponse
import com.example.kisahnabi.databinding.ActivityDetailBinding
import com.example.kisahnabi.ui.MainViewModel

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    companion object{
        const val EXTRA_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val kisah = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA)

        kisah?.let {
            binding.apply {
                detailName.text = kisah.name
                detailDesk.text = kisah.description
                detailTahun.text = kisah.thnKelahiran
                detailTempat.text = kisah.tmp
                detailUsia.text = kisah.usia
                Glide.with(this@DetailActivity).load(kisah.imageUrl).into(detailImage)
            }
        }

    }

}