package com.hui.fragmentpracticedemo.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hui.fragmentpracticedemo.NewsContentActivity
import com.hui.fragmentpracticedemo.R
import com.hui.fragmentpracticedemo.model.News
import com.hui.fragmentpracticedemo.tools.times


/**
 * 新闻标题列表
 */
class NewsTitleFragment : Fragment() {

    private var isTowPANE = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //requireActivity() 返回的是宿主activity
        // 参考：https://blog.csdn.net/Ym_quiet/article/details/121345411
        requireActivity().lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event.targetState == Lifecycle.State.CREATED) {
                    //在这里任你飞翔
                    // 可以做onActivityCreated()生命周期的事
                    isTowPANE = requireActivity().findViewById<View>(R.id.newsContentLayout) != null

                    val layoutManager = LinearLayoutManager(activity)
                    val adapter = NewsAdapter(getNews())
                    val recyclerView =
                        requireView().findViewById<RecyclerView>(R.id.newsTitleRecyclerView)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = adapter

                    // requireActivity().lifecycle.removeObserver(this) //这里是删除观察者
                }
            }
        })
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news = News("新闻标题$i", getRandomLengthString("新闻内容$i "))
            newsList.add(news)
        }
        return newsList
    }

    /**
    private fun getRandomLengthString(s: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(s)
        }
        return builder.toString()
    }
    */
    // 可替换成以下代码
    private fun getRandomLengthString(str:String) = str * (1..20).random()

    // 已弃用
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // 新建一个内部类NewsAdapter来作为RecyclerView的适配器
    inner class NewsAdapter(val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newsList[holder.adapterPosition]
                if (isTowPANE) {
                    // 双页模式
                    val fragment =
                        activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFragment) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    NewsContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount() = newsList.size
    }
}