package com.myapp.mychallenge22_again

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    interface OnFragmentInteractionListener {

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {

            }
    }
}
