package com.yuriy.githubmvvm.ui.fragmants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import com.yuriy.githubmvvm.ui.adapters.ReposListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ReposListFragment : Fragment() {

    private val viewModel: GitHubViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_repos_rec_view.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@ReposListFragment.context)
            adapter = ReposListAdapter(emptyList())
        }

        viewModel.getReposList().observe(viewLifecycleOwner, Observer { repos ->
            id_repos_rec_view.adapter = ReposListAdapter(repos)
        })
    }
}