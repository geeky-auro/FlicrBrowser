package com.aurosaswatraj.myflickrbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

class MainActivity : BaseActivity(),GetRawData.OnDownloadComplete,GetFlickrJsonData.onDataAvailable
,RecyclerOnItemClickListener.onRecyclerClickListener
{

    private val Tag="MainActivity"
    private val flickrRecyclerViewAdapter=FlickrRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        activateToolBar(false)
        // what the list or grid is going to look like
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerOnItemClickListener(this,recycler_view,this))
        recycler_view.adapter=flickrRecyclerViewAdapter

        val url=createUri("https://www.flickr.com/services/feeds/photos_public.gne","cars,bike","en-us",true)
        val getrawdata=GetRawData(this)
        getrawdata.execute(url)

        Log.d(Tag,"OnCreate Ends")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(Tag,"onCreateOptionsMenu is being Called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(Tag,"onOptionsItemSelected is being Called")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data:String,status:DownloadStatus)
    {
        if(status==DownloadStatus.OK)
        {
            Log.d(Tag,"OnDownloadComplete called ")

            val getFlickrJsonData=GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
//            If there is any wrong JSON Data entry then it will cancel in getRawdata
//            getFlickrJsonData.execute("data bogus")

        }
        else
        {
            //Download Failed
            Log.d(Tag,"OnDownloadCompleted Failed with status $status.Error Message is $data")

        }
    }

    //Creating URL#custom url
    private fun createUri(baseURL: String, searchCriteria: String, lang : String, matchAll: Boolean): String? {
        Log.d(Tag,".createUri Starts")
        return Uri.parse(baseURL).buildUpon()//now it's ready to be modified
            .appendQueryParameter("tags",searchCriteria) //any modification you want to make goes here
            .appendQueryParameter("tagmode",if(matchAll)"ALL" else "ANY")
            .appendQueryParameter("lang",lang)
            .appendQueryParameter("format","json")
            .appendQueryParameter("nojsoncallback","1")
            .build().toString()// you have to build it back cause you are storing it
        // as Uri not Uri.builder

    }

    override fun onDataAvailable(data: List<Photo>) {

        Log.d(Tag,".onDataAvailable called ")
        flickrRecyclerViewAdapter.loadNewData(data)
        Log.d(Tag,"onDataAvailable ends")
    }
    override fun onError(exception: Exception) {
        Log.e(Tag,"onError called with ${exception.message}")
    }

    override fun onItemClickListener(view: View, position: Int) {
        Log.d(Tag,"onItemClick Starts")
        Toast.makeText(this,"Normal Tap at position $position",Toast.LENGTH_SHORT).show()

    }

    override fun onItemLongClickListener(view: View, position: Int) {
        Log.d(Tag,"onItemLongClick Starts")
       val photo=flickrRecyclerViewAdapter.getphoto(position)
        if (photo!=null)
        {
            val intent =Intent(this,PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER,photo)
            startActivity(intent)
        }
    }

}