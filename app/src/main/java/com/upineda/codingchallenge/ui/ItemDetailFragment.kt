package com.upineda.codingchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*


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

    private var test: Track = Track("helloo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey("track")) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //          item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                // item = test

                test = it.getParcelable("track")

                activity?.toolbar_layout?.title = test?.trackName
                //  activity?.track_image = test?.trackName



             /*   Picasso.get()
                    .load(test?.artworkUrl100)
                    // .resize(50, 50)
                    // .centerCrop()
                    .into(object : com.squareup.picasso.Target {
                        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                           // activity?.toolbar_layout?.background = BitmapDrawable(bitmap)
                        }

                        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {

                        }

                        override fun onPrepareLoad(placeHolderDrawable: Drawable) {

                        }
                    })*/
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        /*       item?.let {
                   rootView.item_detail.text = it.artistName
               }*/

        rootView.item_detail.text = test.longDescription

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
