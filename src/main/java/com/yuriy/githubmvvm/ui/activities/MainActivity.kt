package com.yuriy.githubmvvm.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    private val listFragment by lazy { ReposListFragment() }
    private val detailsFragment by lazy { UserDetailsFragment() }
    private val viewModel by lazy {
        val factory = GitHubViewModelFactory(Repository.getInstance())
        ViewModelProvider(this, factory).get(GitHubViewModel::class.java)
    }
    private val inputDialog by lazy { FindUserDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            if (viewModel.lastLogin.isNullOrEmpty()) {
                inputDialog.show(supportFragmentManager, "InputDialogFragment")
            } else {
                val user = viewModel.lastLogin
                viewModel.findUser(user!!)
            }
            changeFragment(detailsFragment)
        } else {
            val selectedTab = savedInstanceState.getInt("SELECTED_TAB")
            val tab = id_tab_layout.getTabAt(selectedTab)
            id_tab_layout.selectTab(tab, true)
        }

        btn_search.setOnClickListener {
            inputDialog.show(supportFragmentManager, "InputDialogFragment")
        }

        id_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                changeFragment(getFragmentByIndex(tab.position))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("SELECTED_TAB", id_tab_layout.selectedTabPosition)
        Log.d("UI", "tab position is ${id_tab_layout.selectedTabPosition}")
        super.onSaveInstanceState(outState)
    }

    override fun findUser(username: String) {
        viewModel.findUser(username)
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
