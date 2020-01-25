package com.nhick.porcelain.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nhick.porcelain.R
import com.nhick.porcelain.dashboard.IDashboardCallback
import com.nhick.porcelain.databinding.RecyclerViewBannerBinding
import com.nhick.porcelain.repository.Banner
import com.nhick.porcelain.repository.DataRepository
import kotlinx.android.synthetic.main.recycler_view_banner.view.*

class BannerAdapter(private val listener : IDashboardCallback.onBannerListener) : RecyclerView.Adapter<BannerAdapter.ViewHolder>(){

    private val bannerList = DataRepository().getListOfBanner


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerViewBannerBinding>(LayoutInflater.from(parent.context),
            R.layout.recycler_view_banner,parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bannerList[position])

        holder.itemView.image_view_more.setOnClickListener {
            listener.onMoreClicked(bannerList[position])
        }
    }


    class ViewHolder(private val view:RecyclerViewBannerBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(banner: Banner){
            view.banner = banner
            view.executePendingBindings()
        }

    }


}

