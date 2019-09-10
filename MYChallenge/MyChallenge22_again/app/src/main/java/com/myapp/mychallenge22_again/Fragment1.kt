package com.myapp.mychallenge22_again

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_fragment1.*

class Fragment1 : Fragment() {

    lateinit var callback: onDatabaseCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback = activity as onDatabaseCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_fragment1, container, false) as ViewGroup

        button.setOnClickListener {
            callback.insert(editText.text.toString(), editText2.text.toString(), editText3.text.toString())
            Toast.makeText(context, "added bookinfo", Toast.LENGTH_SHORT).show()
        }

        return rootView
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
