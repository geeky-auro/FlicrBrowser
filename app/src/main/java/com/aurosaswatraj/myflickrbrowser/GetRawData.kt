package com.aurosaswatraj.myflickrbrowser

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus{
    OK,IDLE,NOT_INITIALIZED,FAILED_OR_EMPTY,PERMISSION_ERROR,ERROR
}

class GetRawData(private var listener:OnDownloadComplete) : AsyncTask<String, Void, String>() {

    interface OnDownloadComplete{
        fun onDownloadComplete(data:String,status: DownloadStatus)
    }

    private val Tag="GetRawData"
    private var downloadStatus=DownloadStatus.IDLE

    override fun doInBackground(vararg params: String?): String {
        if(params[0]==null)
        {
            downloadStatus=DownloadStatus.NOT_INITIALIZED
            return "No URL Specified"
        }
        try{
            downloadStatus=DownloadStatus.OK
            return URL(params[0]).readText()
        } catch(e:Exception)
        {
            val errorMessage=when(e){
                is MalformedURLException ->{downloadStatus=DownloadStatus.NOT_INITIALIZED
                    "doInBackground :Invalid URL ${e.message}"}

                is IOException ->{downloadStatus=DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground :IO Exception reading data: ${e.message}"}

                is SecurityException->{downloadStatus=DownloadStatus.PERMISSION_ERROR
                    "doInBackground :Security exception:Needs Permission ${e.message}"}

                else->{downloadStatus=DownloadStatus.ERROR
                    "Unknown Error : ${e.message}"}
            }
            Log.e(Tag,errorMessage)
            return errorMessage
        }
    }

    override fun onPostExecute(result: String) {

        listener.onDownloadComplete(result,downloadStatus)
    }

}