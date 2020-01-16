package com.yuriy.githubmvvm.ui.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.data.entities.UserInfo
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class UserDetailsFragment : Fragment() {

    private val viewModel by lazy {
        activity.run {
            ViewModelProviders.of(this@UserDetailsFragment)[GitHubViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo().observe(this, Observer { info ->
            updateUI(info)
        })
    }

    private fun updateUI(info: UserInfo) = with(info) {
        tv_login.text = login
        tv_type.text = type
        tv_public_repos.text = getString(R.string.public_repos_d, publicRepos)
        tv_followers.text = getString(R.string.followers_d, followers)
        tv_following.text = getString(R.string.following_d, following)
        tv_created.text = getString(R.string.created_at_s, createdAt)
        tv_updated.text = getString(R.string.updated_at_s, updatedAt)

        if (email != null) {
            tv_email.text = getString(R.string.email_s, email)
        }
    }
}