package com.aurosaswatraj.myflickrbrowser

import android.os.Bundle
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_photo_details.*
import kotlinx.android.synthetic.main.content_photo_details.*


class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_photo_details)

        activateToolBar(true)


//        val photo=intent.getSerializableExtra(PHOTO_TRANSFER) as Photo
        val photo=intent.extras?.getParcelable<Photo>(PHOTO_TRANSFER) as Photo
        photo_author.text=photo.author
        photo_tags.text=photo.tags
        photo_title.text=photo.title

        Picasso.get().load(photo.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(photo_image)




        }
    }


