package com.vkompaniets.githubreader.ui.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.ui.MainActivity

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val user = view.findViewById<EditText>(R.id.user)
        val btn = view.findViewById<Button>(R.id.search_btn)
        btn.setOnClickListener { (activity as MainActivity).searchClicked(user.text.toString()) }

        return view
    }


}