package com.example.newsbreezeapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbreezeapp.R
import com.example.newsbreezeapp.modelData.Article
import com.example.newsbreezeapp.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.items_layout.view.*


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    lateinit var viewModel: NewsViewModel

    inner class ArticleViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)

    private val differCallbacks=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
          return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    //now async list differ calculates difference between two lists and only update those
    val differ=AsyncListDiffer(this,differCallbacks)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(
           R.layout.items_layout,parent,false
       ))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)

            tvTitle.text=article.title
            tvDescription.text=article.description
            onItemClickListener?.let{
                it(article)
            }
            buttonSave.setOnClickListener {
                viewModel.saveArticle(article)
                Snackbar.make(this,"Article saved succesfully", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private var onItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener=listener
    }

}