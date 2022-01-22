package com.task.android.ui.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.task.android.R
import com.task.android.databinding.FragmentLoginBinding
import com.task.android.utils.gone
import com.task.android.utils.navigate
import com.task.android.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this

        binding?.getStarted?.setOnClickListener { navigate(R.id.createNearAccountFragment) }

        handleToggleLayout()

    }

    private fun handleToggleLayout() {
        binding?.toggleLayout?.setOnSelectListener { button ->

            when (button.id) {
                R.id.btnEmail -> {
                    binding?.email?.show()
                    binding?.email?.requestFocus()

                    binding?.phone?.gone()
                }
                R.id.btnPhone -> {
                    binding?.email?.gone()

                    binding?.phone?.show()
                    binding?.phone?.requestFocus()
                }
            }

        }

        binding?.toggleLayout?.selectButton(R.id.btnEmail)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}