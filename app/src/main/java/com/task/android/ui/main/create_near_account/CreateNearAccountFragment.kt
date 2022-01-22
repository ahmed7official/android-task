package com.task.android.ui.main.create_near_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task.android.R
import com.task.android.databinding.FragmentCreateNearAccountBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateNearAccountFragment : Fragment() {

    private val viewModel by viewModels<CreateNearAccountViewModel>()

    private var binding: FragmentCreateNearAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_near_account,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        binding?.close?.setOnClickListener { findNavController().navigateUp() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}