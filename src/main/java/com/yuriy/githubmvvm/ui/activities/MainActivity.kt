package com.yuriy.githubmvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import com.yuriy.githubmvvm.mvvm.GitHubViewModelFactory
import com.yuriy.githubmvvm.mvvm.Repository
import com.yuriy.githubmvvm.ui.fragmants.UserDetailsFragment
import com.yuriy.githubmvvm.ui.fragmants.FindUserDialog
import com.yuriy.githubmvvm.ui.fragmants.ReposListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FindUserDialog.InputDialogListener {

    val listFragment by lazy { ReposListFragment()}
    val detailsFragment by lazy {UserDetailsFragment()}
    val viewModel by lazy {
        val factory = GitHubViewModelFactory(Repository.getInstance())
        ViewModelProviders.of(this, factory).get(GitHubViewModel::class.java)
    }
    val inputDialog by lazy {FindUserDialog(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            inputDialog.show(supportFragmentManager, "InputDialogFragment")
        }

        if (fragment_container != null) {
            with(supportFragmentManager) {
                beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit()
            }
        }
    }

    override fun findUser(username: String) {
        viewModel.updateData(username)
    }
}
