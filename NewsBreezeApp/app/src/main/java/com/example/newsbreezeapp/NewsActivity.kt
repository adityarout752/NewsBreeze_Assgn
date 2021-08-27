package com.example.newsbreezeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsbreezeapp.db.ArticleDatabase
import com.example.newsbreezeapp.repository.NewsRepository
import com.example.newsbreezeapp.viewModel.NewsViewModel
import com.example.newsbreezeapp.viewModel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository= NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepository)
        viewModel= ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostfragment.findNavController())
    }
}