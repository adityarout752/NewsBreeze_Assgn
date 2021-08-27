package com.example.newsbreezeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.navigation.fragment.navArgs
import com.example.newsbreezeapp.NewsActivity
import com.example.newsbreezeapp.R
import com.example.newsbreezeapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_articles.*


class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    lateinit var viewModel: NewsViewModel
    private val args:ArticlesFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //now we have access to view model created in news activity
        viewModel=(activity as NewsActivity).viewModel

        //recive the article that was passed as arguments into fragment we do the steps 1 and 2
        val article=args.articles

        webView.apply {
            webChromeClient= WebChromeClient()
            loadUrl(article.url)
        }




}}