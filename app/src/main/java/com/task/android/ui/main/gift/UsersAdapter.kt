package com.task.android.ui.main.gift

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.android.R
import com.task.android.databinding.ListitemUserBinding
import com.task.android.utils.OnClick
import com.task.domain.model.user.User

class UsersAdapter(
    private val initUsers: List<User>,
    var onUserSelect: OnClick<User>? = null,
    var onUserUnselect: OnClick<User>? = null,
) : RecyclerView.Adapter<UsersAdapter.Companion.UserHolder>(), Filterable {

    private val users: ArrayList<User> = arrayListOf()

    private val selectedUsers = arrayListOf<User>()

    init {
        users.addAll(initUsers)
    }

    companion object {
        class UserHolder(val binding: ListitemUserBinding) : RecyclerView.ViewHolder(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding: ListitemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.listitem_user,
            parent,
            false
        )
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val binding = holder.binding
        val user = users[position]

        binding.name.text = user.name ?: ""

        binding.username.text = buildString {
            append("@")
            append(user.username ?: "")
        }

        if (selectedUsers.contains(user)) {
            binding.checkState.setImageResource(R.drawable.ic_check)
        } else {
            binding.checkState.setImageResource(R.drawable.ic_uncheck)
        }

        binding.container.setOnClickListener {
            if (selectedUsers.contains(user)) {
                selectedUsers.remove(user)
                onUserUnselect?.invoke(user, position)
            } else {
                selectedUsers.add(user)
                onUserSelect?.invoke(user, position)
            }
            notifyItemChanged(position)
        }

    }

    override fun getItemCount() = users.size

    override fun getFilter() = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val query = constraint.toString()
            val results = arrayListOf<User>()

            if (query.isBlank()) {
                results.clear()
                results.addAll(initUsers)
            } else {

                initUsers.forEach {
                    if (
                        it.name.toString().contains(query, ignoreCase = true) ||
                        it.username.toString().contains(query, ignoreCase = true) ||
                        it.email.toString().contains(query, ignoreCase = true)
                    ) {
                        results.add(it)
                    }
                }
            }
            return FilterResults().apply { values = results }
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val data = results?.values as List<User>
            users.clear()
            users.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun getSelectedUsers() = selectedUsers

}