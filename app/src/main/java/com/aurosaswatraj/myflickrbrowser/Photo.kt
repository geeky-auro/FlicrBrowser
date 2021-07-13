package com.aurosaswatraj.myflickrbrowser

import android.os.Parcel
import android.os.Parcelable


class Photo(
    var title:String?, var author:String?, var authorId:String?, var link:String?, var tags: String?,
    var image: String?
):Parcelable {

    private val Tag="Photo"

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){}

//Instead of Serializable we used parceble....


//    In addition to implementing the Serializable interface, a class intended for
//    serialization should also contain a private static final long
//    variable named serialVersionUID.
//
//
//    companion object{
//        private const val serailVersionUID=1L
//    }
//

//    The serialVersionUID variable is used by Java's object serialization API
//    to determine if a deserialized object was serialized (written) with
//    the same version of the class, as it is now attempting to deserialize it into.

    override fun toString(): String {
        return "Photo(title:$title,authors:$author,authorId:$authorId,link:$link,tags:$tags,image:$image)"
    }

//    The ObjectOutputStream class contains writeObject()
//    method for serializing an Object.
//
//    @Throws(IOException::class)
//    private fun writeObject(out:java.io.ObjectOutputStream){
//        Log.d(Tag,".writeObject called")
//        out.writeUTF(title)
//        out.writeUTF(author)
//        out.writeUTF(authorId)
//        out.writeUTF(link)
//        out.writeUTF(tags)
//        out.writeUTF(image)
//    }


//    The ObjectInputStream class contains readObject()
//    method for deserializing an object.

//    @Throws(IOException::class,ClassNotFoundException::class)
//    private fun readObject(instream:java.io.ObjectInputStream){
//        Log.d(Tag,".readObject called")
//        title=instream.readUTF()
//        author=instream.readUTF()
//        authorId=instream.readUTF()
//        link=instream.readUTF()
//        tags=instream.readUTF()
//        image=instream.readUTF()
//    }


//    @Throws(IOException::class)
//    private fun readObjectNoData(){
//        Log.d(Tag,".readObjectNoData called")
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(authorId)
        parcel.writeString(link)
        parcel.writeString(tags)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }


}