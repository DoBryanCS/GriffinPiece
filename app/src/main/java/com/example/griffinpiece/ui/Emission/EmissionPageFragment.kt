package com.example.griffinpiece.ui.Emission

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.griffinpiece.R
import com.example.griffinpiece.adapters.ShowsRecyclerViewAdapter
import com.example.griffinpiece.ui.accueil.ShowsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmissionPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmissionPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_shows, container, false)
        val activity = activity as Context
        val id = this.requireArguments().getInt("id")


        return view
    }
}