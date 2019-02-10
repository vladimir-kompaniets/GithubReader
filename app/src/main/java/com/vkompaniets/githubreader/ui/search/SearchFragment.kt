package com.vkompaniets.githubreader.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.vkompaniets.githubreader.GithubReaderApp
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.di.ViewModelFactory
import com.vkompaniets.githubreader.ui.MainNavigator
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), SearchResultsAdapter.UserClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: SearchViewModel

    private lateinit var adapter: SearchResultsAdapter

    private lateinit var navigator: MainNavigator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigator = activity as? MainNavigator
            ?: throw IllegalStateException("Hosting activity should implement MainNavigator contract")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        GithubReaderApp.instance.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        searchBtn.setOnClickListener {
            viewModel.searchForUsers(input.text.toString())
            hideKeyboard(it.windowToken)
        }

        adapter = SearchResultsAdapter(this)
        userList.adapter = adapter
        userList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel.searchResult.observe(this, Observer {
            it?.let {
                adapter.setItems(it.data)
                progressBar.visibility = if (it.loading) View.VISIBLE else View.GONE
                errorMsg.text = it.errorMessage
            }
        })
    }

    override fun onUserClicked(login: String) {
        navigator.showUserDetails(login)
    }

    private fun hideKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}