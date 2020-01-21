package com.nhick.porcelain.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.nhick.porcelain.R
import com.nhick.porcelain.dashboard.adapter.ArticleAdapter
import com.nhick.porcelain.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var bind : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bind = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.fragment_home,container,false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.recyclerViewArticles.adapter = ArticleAdapter(requireContext())
        bind.recyclerViewArticles.layoutManager = LinearLayoutManager(requireContext(),HORIZONTAL,false)


    }

}