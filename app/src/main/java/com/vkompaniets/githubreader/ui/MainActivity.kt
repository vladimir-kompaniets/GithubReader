package com.vkompaniets.githubreader.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.ui.search.SearchFragment
import com.vkompaniets.githubreader.ui.user.UserDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content, SearchFragment()).commit()
        }
    }

    override fun showUserDetails(login: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, UserDetailsFragment.create(login))
            .addToBackStack(null)
            .commit()
    }



}
