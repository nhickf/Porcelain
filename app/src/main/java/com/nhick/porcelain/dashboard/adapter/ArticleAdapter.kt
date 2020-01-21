package com.nhick.porcelain.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nhick.porcelain.R
import com.nhick.porcelain.databinding.RecyclerViewArticlesBinding
import com.nhick.porcelain.repository.Articles
import com.nhick.porcelain.repository.DataRepository
import java.util.zip.Inflater

class ArticleAdapter(private val context:Context) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val articles = DataRepository().getListOfArticles
    private lateinit var bind : RecyclerViewArticlesBinding
    private lateinit var requestOptions : RequestOptions

    init {

       requestOptions()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

         bind = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recycler_view_articles,parent,false)

        return ViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = articles[position]

        holder.bind(article)

        Glide.with(context)
            .applyDefaultRequestOptions(requestOptions)
            .load(article.articleUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(bind.imageViewArticles)

    }

    private fun requestOptions(){

        requestOptions = RequestOptions()
            .centerCrop()
            .placeholder(context.getDrawable(R.drawable.ic_broken_image_24dp))


    }

    class ViewHolder(private val binding: RecyclerViewArticlesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(articles: Articles){
            binding.article = articles
            binding.executePendingBindings()
        }

    }
}