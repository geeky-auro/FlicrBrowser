package com.aurosaswatraj.myflickrbrowser

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class GetFlickrJsonData(val listener:onDataAvailable) : AsyncTask<String, Void, ArrayList<Photo>>() {

    private val Tag="GetFlickrJsonData"

    interface onDataAvailable{
        fun onDataAvailable(data:List<Photo>)
        fun onError(exception: Exception)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Log.d(Tag,"doInBackground Starts")
        val photoList=ArrayList<Photo>()
        try{
            val jsonData = JSONObject(params[0])
            val itemArrays = jsonData.getJSONArray("items")
            for (i in 0 until itemArrays.length())
            {
                val jsonPhoto=itemArrays.getJSONObject(i)
                val title=jsonPhoto.getString("title")

                val author=jsonPhoto.getString("author")
                val author_id=jsonPhoto.getString("author_id")
                val tags=jsonPhoto.getString("tags")
                val JsonMedia=jsonPhoto.getJSONObject("media")
                val photoUrl=JsonMedia.getString("m")
                val link=photoUrl.replace("_m.jpg","_b.jpg")

                val photoObject=Photo(title ,author,author_id,link,tags,photoUrl)

                photoList.add(photoObject)
                Log.d(Tag,"doInBackground $photoObject")
            }
        }catch (e: JSONException)
        {
            e.printStackTrace()
            Log.e(Tag,"doInBackground Error processing JSON Data.${e.message}")
            cancel(true)
            listener.onError(e)
            Log.d(Tag,".doInBackground Ends.")
        }
        return photoList    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(Tag,"onPostExecute Starts")
        super.onPostExecute(result)
        listener.onDataAvailable(result)
        Log.d(Tag,".OnPostExecute Ends")
    }
}