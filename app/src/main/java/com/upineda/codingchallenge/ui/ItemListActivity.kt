package com.upineda.codingchallenge.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.upineda.codingchallenge.*

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val tracksAdapter = TracksListAdapter()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)



        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        item_list.adapter = tracksAdapter

        viewModel.tracksState.observe(this, Observer {
            when (it) {
                is TracksState.Complete -> tracksAdapter.submitList(it.data.results)
            }
        })

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        val iFilter = IntentFilter()
        iFilter.addAction(DETAILED_TRACK_RECEIVER)
        registerReceiver(trackDetailsReceiver, iFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(trackDetailsReceiver)
        super.onDestroy()
    }

    private val trackDetailsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val track = intent?.getParcelableExtra<Track>(ItemDetailFragment.TRACK_RESULT)

            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ItemDetailFragment.TRACK_RESULT, track)
                    }
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(context, ItemDetailActivity::class.java).apply {
                    putExtra(ItemDetailFragment.TRACK_RESULT, track)
                }
                context?.startActivity(intent)
            }
        }
    }

    companion object {
        const val DETAILED_TRACK_RECEIVER = "com.upineda.codingchallenge.trackReceiver"
    }
}
