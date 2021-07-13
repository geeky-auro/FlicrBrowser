package com.aurosaswatraj.myflickrbrowser

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private val Tag="SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(Tag,"onCreate Starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolBar(true)
        Log.d(Tag,"oncreate Ends")
        }
    }


