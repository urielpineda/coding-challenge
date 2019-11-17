package com.upineda.codingchallenge.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.upineda.codingchallenge.*
import com.upineda.codingchallenge.databinding.ActivityItemListBinding
import com.upineda.codingchallenge.viewmodels.TracksViewModel

import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import java.util.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    lateinit var listViewModel: TracksViewModel
    private val tracksAdapter = TracksListAdapter()

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listViewModel = ViewModelProviders.of(this).get(TracksViewModel::class.java)

        DataBindingUtil.setContentView<ActivityItemListBinding>(
            this, R.layout.activity_item_list
        ).apply {
            this.lifecycleOwner = this@ItemListActivity
            this.viewModel = listViewModel
        }

        val pref = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val dateExited = pref.getString(LAST_VISITED, "")
        if (dateExited.isNotEmpty()) {
            listViewModel.dateExited = dateExited
            last_visited.visibility = View.VISIBLE
        }

        item_list.adapter = tracksAdapter

        // observes our list view model and displays an error message, loading screen, or
        // the list of tracks
        listViewModel.tracksState.observe(this, Observer {

            prgTracks.visibility = if (it is TracksState.Loading) View.VISIBLE else View.GONE
            frameLayout.visibility = if (it is TracksState.Complete) View.VISIBLE else View.GONE
            errTracks.visibility = if (it is TracksState.Error) View.VISIBLE else View.GONE

            when (it) {
                is TracksState.Complete -> {
                    var results = it.data.results
                    results = results.filter { it.trackName.isNotEmpty() }
                    tracksAdapter.submitList(results)
                }
            }
        })

        setSupportActionBar(toolbar)

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

    override fun onStop() {
        saveData()
        super.onStop()
    }

    override fun onDestroy() {
        unregisterReceiver(trackDetailsReceiver)
        super.onDestroy()
    }

    /** We receive the broadcast from track onClick in tracks list adapter
     * and load the correct detailed view depending on the device's size
     */
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

    /** Saves date last visited */
    fun saveData() {
        val pref = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        val timeStamp: String = Date().toString()
        val editor = pref.edit()
        editor.putString(LAST_VISITED,  resources.getString(R.string.date_last_visited) + timeStamp)
        editor.apply()
    }

    companion object {
        const val DETAILED_TRACK_RECEIVER = "com.upineda.codingchallenge.trackReceiver"
        const val LAST_VISITED = "LastVisit"
        const val PREFERENCES = "CodingChallenge"
    }
}
