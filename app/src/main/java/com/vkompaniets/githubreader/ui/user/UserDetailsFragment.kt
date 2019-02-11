package com.vkompaniets.githubreader.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.vkompaniets.githubreader.GithubReaderApp
import com.vkompaniets.githubreader.R
import com.vkompaniets.githubreader.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_user_details.*
import javax.inject.Inject

class UserDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: UserDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        GithubReaderApp.instance.appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserDetailsViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.loadData(arguments?.getString(ARG_USER_LOGIN) ?: throw IllegalArgumentException("User login must be provided"))
        }

        val adapter = ReposAdapter()
        repoList.adapter = adapter

        viewModel.userResult.observe(this, Observer { resource ->
            resource?.let {
                it.errorMessage?.let { errorMsg -> showToast(errorMsg) }
                if (it.data == null) {
                    name.text = null
                    followers.text = null
                    following.text = null
//                    avatar.setImageResource(0)
                } else {
                    val user = it.data
                    name.text = if (user.company == null) user.login else resources.getString(R.string.name_company, user.login, user.company)
                    followers.text = resources.getString(R.string.followers, user.followers)
                    following.text = resources.getString(R.string.following, user.following)
                    Picasso.get().load(user.avatar).into(avatar)
                }

            }
        })

        viewModel.reposResult.observe(this, Observer { resource ->
            resource?.let {
                it.errorMessage?.let { errorMsg -> showToast(errorMsg) }
                adapter.setItems(it.data)
            }
        })
    }

    private fun showToast(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

    companion object {
        private const val ARG_USER_LOGIN = "ARG_USER_LOGIN"

        fun create(userLogin: String): UserDetailsFragment = UserDetailsFragment()
            .apply {
                arguments = Bundle().apply { putString(ARG_USER_LOGIN, userLogin) }
            }
    }

}