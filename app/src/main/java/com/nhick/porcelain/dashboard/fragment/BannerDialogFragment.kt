package com.nhick.porcelain.dashboard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.nhick.porcelain.R
import com.nhick.porcelain.databinding.DialogFragmentBannerBinding
import com.nhick.porcelain.repository.Banner

class BannerDialogFragment : DialogFragment() {


    private lateinit var binding : DialogFragmentBannerBinding
    private lateinit var banner : Banner

    companion object{

        fun newInstance(banner: Banner) : BannerDialogFragment{
            val bannerDialogFragment = BannerDialogFragment()
            bannerDialogFragment.banner = banner
            return bannerDialogFragment
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_banner,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.banner = banner


    }

}