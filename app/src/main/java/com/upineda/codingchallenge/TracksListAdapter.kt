package com.upineda.codingchallenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upineda.codingchallenge.ItemDetailFragment.Companion.TRACK_RESULT
import com.upineda.codingchallenge.ui.ItemListActivity.Companion.DETAILED_TRACK_RECEIVER
import kotlinx.android.synthetic.main.item_list_content.view.*

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

        if(track.trackName.isEmpty())
            return

        holder.itemView.track_name.text = track.trackName

        if(track.price.toFloatOrNull() != null)
            holder.itemView.price.text = track.price + " " + track.currency
        else
            holder.itemView.price.text = track.price

        holder.itemView.genre.text = track.genre

        Picasso.get()
            .load(track.artworkUrl100)
            .into(holder.itemView.image_view)

        holder.itemView.setOnClickListener {
            val intent = Intent(DETAILED_TRACK_RECEIVER)
            intent.putExtra(TRACK_RESULT, track)
            it.context?.sendBroadcast(intent)
        }
    }
}