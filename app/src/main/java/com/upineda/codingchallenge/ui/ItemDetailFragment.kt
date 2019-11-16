package com.upineda.codingchallenge

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import android.widget.TextView
import android.text.style.ForegroundColorSpan
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.util.Size


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */

    private lateinit var track: Track

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(TRACK_RESULT)) {
                track = it.getParcelable(TRACK_RESULT)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        activity?.toolbar_layout?.title = track?.trackName

        Picasso.get()
            .load(track?.artworkUrl100)
            .into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    activity?.toolbar_layout?.background = BitmapDrawable(bitmap)
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {}

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
            })


        val builder = SpannableStringBuilder()
        val str1 = SpannableString("Name: " + track.trackName + " \n" +
                                   "Price: " + track.price + " \n" +
                                   "Genre: " + track.genre + " \n\n")
        str1.setSpan(RelativeSizeSpan(0.7f), 0, str1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.append(str1)

        builder.append(track.longDescription)

        rootView.item_detail.setText(builder, TextView.BufferType.SPANNABLE)

        return rootView
    }

    companion object {
        const val TRACK_RESULT = "track"
    }
}
