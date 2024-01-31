package com.hui.fragmentpracticedemo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hui.fragmentpracticedemo.databinding.ActivityMainBinding
import com.hui.fragmentpracticedemo.databinding.ActivityNewsContentBinding
import com.hui.fragmentpracticedemo.fragment.NewsContentFragment

class NewsContentActivity : AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        // 获取传递过来的标题/新闻内容
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if (title != null && content != null) {
            // 显示标题/新闻内容
            val fragment =
                supportFragmentManager.findFragmentById(R.id.newsContentFragment) as NewsContentFragment
            fragment.refresh(title, content)
        }
    }
}