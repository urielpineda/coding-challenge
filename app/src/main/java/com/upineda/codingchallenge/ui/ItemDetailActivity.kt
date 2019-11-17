package com.upineda.codingchallenge.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.upineda.codingchallenge.*
import com.upineda.codingchallenge.databinding.ActivityItemDetailBinding
import com.upineda.codingchallenge.viewmodels.TrackViewModel
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val trackViewModel = ViewModelProviders.of(this)
            .get(TrackViewModel::class.java)

        DataBindingUtil.setContentView<ActivityItemDetailBinding>(
            this, R.layout.activity_item_detail
        ).apply {
            this.lifecycleOwner = this@ItemDetailActivity
            this.viewModel = trackViewModel
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    val res: Track = intent.getParcelableExtra(ItemDetailFragment.TRACK_RESULT)

                    putParcelable(ItemDetailFragment.TRACK_RESULT, res)
                    trackViewModel.detailTitle = res.trackName

                    Picasso.get()
                        .load(res.artworkUrl100)
                        .into(object : com.squareup.picasso.Target {
                            override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                                trackViewModel.img = BitmapDrawable(bitmap)
                            }

                            override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {}

                            override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
                        })
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                navigateUpTo(Intent(this, ItemListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
