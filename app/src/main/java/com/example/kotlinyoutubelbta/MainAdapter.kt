package com.example.kotlinyoutubelbta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.video_row.view.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL


class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("first", "second", "third")

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val videoTitle = videoTitles.get(position)

        val video = homeFeed.videos[position]
        val url = URL(video.imageUrl)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        holder.itemView.textView_video_title.text = video.name
        holder.itemView.imageView2.setImageBitmap(bmp)



    }

}

class CustomViewHolder(view : View): RecyclerView.ViewHolder(view) {

}

