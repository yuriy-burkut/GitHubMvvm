package com.yuriy.githubmvvm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuriy.githubmvvm.R
import com.yuriy.githubmvvm.data.entities.GitHubRepo
import kotlinx.android.synthetic.main.list_item.view.*

class ReposListAdapter(private val repos: List<GitHubRepo>) : RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bind(item: GitHubRepo) = with(itemView) {
            tv_repo_name.text = item.name
            tv_issues.text = resources.getString(R.string.open_issues_d, item.openIssues)
            tv_branch.text = resources.getString(R.string.default_branch_s, item.defaultBranch)
        }
    }
}