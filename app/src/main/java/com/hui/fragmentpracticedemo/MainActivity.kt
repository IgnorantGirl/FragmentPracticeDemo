package com.hui.fragmentpracticedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hui.fragmentpracticedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding:ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val binding = DataBindingUtil.setContentView<ActivitMainBinding>(this,R.layout.activity_main)

        setContentView(R.layout.activity_main)
    }
}