package com.aurosaswatraj.myflickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerOnItemClickListener(context:Context, recyclerView: RecyclerView,private val listener: onRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener()

{

    private val Tag="RecyclerOnItemClickListy"

    interface onRecyclerClickListener{
        fun onItemClickListener(view:View,position:Int)
        fun onItemLongClickListener(view:View,position: Int)
    }
    //add gesture detector
    private val gestureDetector=GestureDetector(context,object : GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(Tag,"onSingleTapUp starts")
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            Log.d(Tag,"onSingleTapUp calling listener.onItemClick")
            if (childView != null) {
                listener.onItemClickListener(childView,recyclerView.getChildAdapterPosition(childView))
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(Tag,"OnLongPress Starts")
            val childView=recyclerView.findChildViewUnder(e.x,e.y)
            Log.d(Tag,"onLongPress calling listener.onItemLongClick")
            if (childView != null) {
                listener.onItemLongClickListener(childView,recyclerView.getChildAdapterPosition(childView))
            }
        }


    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
       Log.d(Tag,"onInterceptTouchEvent starts at $e")
        val result=gestureDetector.onTouchEvent(e)
//        return super.onInterceptTouchEvent(rv, e)
    return result
    }
}