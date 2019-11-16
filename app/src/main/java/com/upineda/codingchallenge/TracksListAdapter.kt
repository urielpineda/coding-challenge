package com.upineda.codingchallenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_content.view.*


/*
*
*
*
 */

class TracksListAdapter :
    ListAdapter<Track, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Track>() {

        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val track = getItem(position)
        holder.itemView.content.text = track.trackName

        holder.itemView.price.text = track.price + " "  + track.currency
        holder.itemView.genre.text = track.genre

        Picasso.get()
            .load(track.artworkUrl100)
            // .resize(50, 50)
            // .centerCrop()
            .into(holder.itemView.image_view)

        //holder.contentView.text = item.content
        holder.itemView.setOnClickListener {
            val intent = Intent("Something")
            intent.putExtra("track", track)
            it.context?.sendBroadcast(intent)
        }
    }
}