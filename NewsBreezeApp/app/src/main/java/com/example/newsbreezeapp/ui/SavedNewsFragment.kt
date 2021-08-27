package com.example.newsbreezeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbreezeapp.Adapter.NewsAdapter
import com.example.newsbreezeapp.NewsActivity
import com.example.newsbreezeapp.R
import com.example.newsbreezeapp.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_news.*


class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //now we have access to view model created in news activity
        viewModel=(activity as NewsActivity).viewModel
        setUpRecyclerView()
        //used to pass data on click of each item by using serializable
        newsAdapter.setOnItemClickListener {
            //we will need to take this in bundle attach it navigation component and pass it in webview
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            //fragment transition happens bellow
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articlesFragment,
                bundle
            )

        }



        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            //whenever item in our databse changes this passes new list automatically obsercer is called
            newsAdapter.differ.submitList(it)
        })
    }
    private fun setUpRecyclerView(){
        newsAdapter= NewsAdapter()
        rvSavedNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }


}