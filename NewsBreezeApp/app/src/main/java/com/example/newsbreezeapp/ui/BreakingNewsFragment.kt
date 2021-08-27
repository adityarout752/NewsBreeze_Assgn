package com.example.newsbreezeapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbreezeapp.Adapter.NewsAdapter
import com.example.newsbreezeapp.NewsActivity
import com.example.newsbreezeapp.R
import com.example.newsbreezeapp.remote.Resource
import com.example.newsbreezeapp.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.*


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {



    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private val TAG = "BREAKINGNEWSFRAGMENT"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //now we have access to view model created in news activity
        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()


        //used to pass data on click of each item by using serializable
        newsAdapter.setOnItemClickListener {
            //we will need to take this in bundle attach it navigation component and pass it in webview
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            //fragment transition happens bellow
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articlesFragment,
                bundle
            )

        }

        //we subscribe to all the changes in live data
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "error is:$message")

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}