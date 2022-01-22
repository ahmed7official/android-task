package com.task.android.ui.main.gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.android.R
import com.task.android.databinding.FragmentGiftBinding
import com.task.android.utils.hideSoftKeyboard
import com.task.android.utils.launchViewLifecycleScope
import com.task.domain.model.user.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class GiftFragment : Fragment() {

    private val viewModel by viewModels<GiftViewModel>()

    private var binding: FragmentGiftBinding? = null

    private var usersAdapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gift, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        hideSoftKeyboard()

        binding?.close?.setOnClickListener { findNavController().navigateUp() }

        collectUsers()

        handleSearchImeClicks()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.users?.adapter = null
        binding = null
        usersAdapter = null
    }

    private fun collectUsers() {
        launchViewLifecycleScope {

            viewModel.users.collect { request ->

                request?.onLoading { showLoadingUi() }

                request?.onSuccess { users -> updateUi(users) }

                request?.onFailure { _, _ ->
                    // TODO: 1/22/2022
                }

            }
        }
    }

    private fun showLoadingUi() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun hideLoadingUi() {
        binding?.loading?.visibility = View.GONE
    }

    private fun handleSearchImeClicks() {
        binding?.query?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard()
                usersAdapter?.filter?.filter(viewModel.query.value)
            }
            false
        }
    }

    private fun updateUi(users: List<User>) {

        usersAdapter = UsersAdapter(initUsers = users)

        binding?.users?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = usersAdapter
        }

        hideLoadingUi()

    }

}