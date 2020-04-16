package com.jaques.projetos.kotlinyoutube

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_lesson_row.view.*
import kotlinx.android.synthetic.main.video_row.view.*

class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First Title", "Second", "3rd", "Moreee Titless")

    //NumberOfItems
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos[position]

        holder.view.textView_video_title.text = video.name
        holder.view.textView_channel_name.text = video.channel.name

        val thumbnailImageView = holder.view.imageView_video_thumbnail
        Picasso.get().load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder.view.imageView_channel_profile
        Picasso.get().load(video.channel.profileImageUrl).into(channelProfileImageView)

        holder.video = video

    }
}

class CustomViewHolder(val view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        const val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        const val VIDEO_ID_KEY = "VIDEO_ID"
    }
    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseDetailActivity::class.java)
            intent.putExtra(VIDEO_TITLE_KEY, video?.name)
            intent.putExtra(VIDEO_ID_KEY, video?.id)

            view.context.startActivity(intent)
        }
    }
}



