package com.yuriy.githubmvvm.ui.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.data.entities.UserInfo
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class UserDetailsFragment : Fragment() {

    private val viewModel: GitHubViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo().observe(viewLifecycleOwner, Observer { info ->
            updateUI(info)
        })
    }

    private fun updateUI(info: UserInfo) = with(info) {

        Picasso.get().load(avatarUrl).into(iv_avatar)

        tv_login.text = getString(R.string.login_name, login)
        tv_type.text = getString(R.string.type, type)
        tv_public_repos.text = getString(R.string.public_repos_d, publicRepos)
        tv_followers.text = getString(R.string.followers_d, followers)
        tv_following.text = getString(R.string.following_d, following)
        tv_created.text = getString(R.string.created_at_s, createdAt)
        tv_updated.text = getString(R.string.updated_at_s, updatedAt)

        if (!email.isNullOrBlank()) {
            tv_email.text = getString(R.string.email_s, email)
            tv_email.visibility = View.VISIBLE
        } else tv_email.visibility = View.GONE
    }
}