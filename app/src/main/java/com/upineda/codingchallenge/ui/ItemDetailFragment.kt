package com.upineda.codingchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.upineda.codingchallenge.databinding.ItemDetailBinding


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
    private lateinit var viewModel: DetailsViewModel

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
       val binding : ItemDetailBinding = DataBindingUtil.inflate(inflater, R.layout.item_detail, container, false)
       viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
       viewModel.setDetails(track)

       binding.viewModel = viewModel
       binding.lifecycleOwner = this
       return binding.root
    }

    companion object {
        const val TRACK_RESULT = "track"
    }
}
