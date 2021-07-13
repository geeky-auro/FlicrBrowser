package com.aurosaswatraj.myflickrbrowser


import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


internal const val FLICKR_QUERY="FLICK_RQUERY"
internal const val PHOTO_TRANSFER="PHOTO_TRANSFER"

open class BaseActivity:AppCompatActivity() {

    private val Tag="BaseActivity"

    internal fun activateToolBar(enableHome:Boolean){
        Log.d(Tag,".activateToolBar")

        val toolbar=findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}