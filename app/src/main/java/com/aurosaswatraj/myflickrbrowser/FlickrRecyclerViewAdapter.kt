package com.aurosaswatraj.myflickrbrowser



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

//ViewHolder Class
class FlickrImageViewHolder(view:View):RecyclerView.ViewHolder(view){
//    1st
    val thumbnail=view.findViewById<ImageView>(R.id.thumbnail)
    val title=view.findViewById<TextView>(R.id.title)
}

//Adapter Class

class FlickrRecyclerViewAdapter(private var photolist:List<Photo>):RecyclerView.Adapter<FlickrImageViewHolder>() {
    private val Tag="FlickeRecyclerViewAdapt"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
//        2nd
//        Called by layout manager when it needs new views..
//        RecyclerView calls this method whenever it needs to create a new ViewHolder
//        Create a new view, which defines the UI of the list item
        Log.d(Tag,".onCreateViewHolder new view requested.")
        val view=LayoutInflater.from(parent.context).inflate(R.layout.browse,parent,false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
//    called by the layout manager when it wants new data in an existing view
//        RecyclerView calls this method to associate a ViewHolder with data.
//        The method fetches the appropriate data
//        and uses the data to fill in the view holder's layout.
        val photoitem=photolist[position]
        Log.d(Tag,".onBindViewHolder : ${photoitem.title}---->position")
        Picasso.get().load(photoitem.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.thumbnail)

        holder.title.text=photoitem.title
    }


    override fun getItemCount(): Int {
//3rd
//        RecyclerView calls this method to get the size of the data set
        // Return the size of your dataset (invoked by the layout manager)
        Log.d(Tag,".getItemCount called!")
        return if (photolist.isNotEmpty()) photolist.size else 0
    }


    fun loadNewData(newPhotos:List<Photo>){
//        4th
        photolist=newPhotos
        notifyDataSetChanged()
    }

    fun getphoto(position:Int):Photo?{
//        5th
    return if (photolist.isNotEmpty()) photolist[position] else null
    }


}