package com.yuriy.githubmvvm.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import com.yuriy.githubmvvm.mvvm.GitHubViewModelFactory
import com.yuriy.githubmvvm.mvvm.Repository
import com.yuriy.githubmvvm.ui.fragmants.FindUserDialog
import com.yuriy.githubmvvm.ui.fragmants.ReposListFragment
import com.yuriy.githubmvvm.ui.fragmants.UserDetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FindUserDialog.InputDialogListener {

    val listFragment by lazy { ReposListFragment() }
    val detailsFragment by lazy { UserDetailsFragment() }
    val viewModel by lazy {
        val factory = GitHubViewModelFactory(Repository.getInstance())
        ViewModelProviders.of(this, factory).get(GitHubViewModel::class.java)
    }
    val inputDialog by lazy { FindUserDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (viewModel.getUserInfo().value == null) {

            inputDialog.show(supportFragmentManager, "InputDialogFragment")
        }

        btn_search.setOnClickListener {
            inputDialog.show(supportFragmentManager, "InputDialogFragment")
        }

        id_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                changeFragment(getFragmentByIndex(tab.position))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                changeFragment(getFragmentByIndex(tab.position))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun findUser(username: String) {
        viewModel.updateData(username)
        changeFragment(detailsFragment)
    }

    private fun getFragmentByIndex(tab: Int): Fragment {
        return when (tab) {
            0 -> detailsFragment
            1 -> listFragment
            else -> detailsFragment
        }
    }

    private fun changeFragment(fragment: Fragment) {
        if (fragment_container != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
