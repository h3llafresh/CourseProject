package com.example.courseproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.courseproject.R
import com.example.courseproject.databinding.FragmentLoginBinding
import com.example.courseproject.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginInputWrapper.typeface = ResourcesCompat.getFont(
            requireContext(),
            R.font.raleway
        )
        binding.passwordInputWrapper.typeface = ResourcesCompat.getFont(
            requireContext(),
            R.font.raleway
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val loginInputText = binding.loginInput.text.toString()
            val passwordInputText = binding.passwordInput.text.toString()
            when {
                loginInputText == "" && passwordInputText == "" -> {
                    binding.loginInputWrapper.error = getString(R.string.empty_login)
                    binding.passwordInputWrapper.error = getString(R.string.empty_password)
                }
                loginInputText == "" -> binding.loginInputWrapper.error =
                    getString(R.string.empty_login)
                passwordInputText == "" -> {
                    binding.passwordInputWrapper.error = getString(R.string.empty_password)
                    binding.loginInputWrapper.error = null
                }
                else -> {
                    binding.loginInputWrapper.error = null
                    binding.passwordInputWrapper.error = null
                    loginViewModel.authorizeUser(loginInputText, passwordInputText, view)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}