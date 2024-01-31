package com.hui.fragmentpracticedemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hui.fragmentpracticedemo.databinding.NewsContentFragBinding


class NewsContentFragment : Fragment() {

    private var mBinding:NewsContentFragBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = NewsContentFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun refresh(title: String, content: String) {
        binding.contentLayout.visibility = View.VISIBLE // 显示内容布局
        binding.newsTitle.text = title // 刷新新闻的标题
        binding.newsContent.text = content // 刷新新闻的内容
    }

}