package com.nhick.porcelain.dashboard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.material.snackbar.Snackbar
import com.nhick.porcelain.R
import com.nhick.porcelain.dashboard.CirclePageIndicator
import com.nhick.porcelain.dashboard.IDashboardCallback
import com.nhick.porcelain.dashboard.adapter.ArticleAdapter
import com.nhick.porcelain.dashboard.adapter.BannerAdapter
import com.nhick.porcelain.databinding.FragmentHomeBinding
import com.nhick.porcelain.repository.Banner

class HomeFragment : Fragment() , View.OnClickListener , IDashboardCallback.onBannerListener {

    private lateinit var bind : FragmentHomeBinding
    private lateinit var callback : IDashboardCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity.let { callback  = context as IDashboardCallback }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.fragment_home,container,false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerViewArticles.adapter = ArticleAdapter(requireContext())
        bind.recyclerViewArticles.layoutManager = LinearLayoutManager(requireContext(),HORIZONTAL,false)

        bind.recyclerViewBanner.adapter = BannerAdapter(this)
        bind.recyclerViewBanner.layoutManager = LinearLayoutManager(requireContext(),HORIZONTAL,false)

        bind.pageIndicator.attachToRecyclerView(bind.recyclerViewBanner)

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(bind.recyclerViewArticles)

        val pagerSnapHelperBanner = PagerSnapHelper()
        pagerSnapHelperBanner.attachToRecyclerView(bind.recyclerViewBanner)

        bind.buttonProducts.setOnClickListener(this)
        bind.buttonTreatment.setOnClickListener(this)
        bind.buttonTimeDate.setOnClickListener(this)


        bind.chipContainer.check(R.id.chip_news)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button_products -> callEmptyFragment("Products")
            R.id.button_treatment -> callEmptyFragment("Treatments")
            R.id.button_time_date -> callEmptyFragment("Appointments")
        }

    }

    private fun callEmptyFragment(title :String){
        callback.onClickedBlankFragment(title,EmptyFragment())
    }

    override fun onMoreClicked(banner: Banner) {
        BannerDialogFragment.newInstance(banner).show(fragmentManager!!,"Dialog")
    }

}