package com.vkompaniets.githubreader.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vkompaniets.githubreader.GithubReaderApp
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.di.ViewModelFactory
import com.vkompaniets.githubreader.ui.search.SearchFragment
import com.vkompaniets.githubreader.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content, SearchFragment()).commit()
        }

        (application as GithubReaderApp).appComponent.inject(this)

        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        searchViewModel.searchResult.observe(this, Observer { it?.forEach { user -> Log.d("XXX", user.login) } })
    }

    fun searchClicked(query: String){
        searchViewModel.searchUser(query)
    }



}
