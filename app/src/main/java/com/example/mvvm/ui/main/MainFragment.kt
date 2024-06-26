package com.example.mvvm.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.mvvm.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener {
            binding.searchButton.isEnabled = binding.editText.text.toString().length >= 3
        }

        binding.searchButton.setOnClickListener {
            viewModel.onStartSearch(binding.editText.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.NoText -> {
                        binding.progressBar.isVisible = false
                        binding.searchButton.isEnabled = false
                        binding.resultView.text = "Результат поиска ->"
                    }

                    is State.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.searchButton.isEnabled = false
                    }

                    is State.Success -> {
                        binding.progressBar.isVisible = false
                        binding.searchButton.isEnabled = true
                        binding.resultView.text = "По запросу ${state.result} ничего не найдено"
                    }
                }
            }
        }
    }
}
