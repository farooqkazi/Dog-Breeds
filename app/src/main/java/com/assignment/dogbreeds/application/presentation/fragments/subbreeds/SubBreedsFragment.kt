package com.assignment.dogbreeds.application.presentation.fragments.subbreeds

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.dogbreeds.R
import com.assignment.dogbreeds.application.presentation.common.EmptyStatePresenter
import com.assignment.dogbreeds.application.presentation.common.viewBinding
import com.assignment.dogbreeds.databinding.FragmentDogsSubBreedsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SubBreedsFragment : Fragment(R.layout.fragment_dogs_sub_breeds_list),
    EmptyStatePresenter {
    override fun getEmptyStateLayout() = binding.emptyLayout
    private val binding by viewBinding<FragmentDogsSubBreedsListBinding>()
    private val viewModel by viewModels<DogsSubBreedsViewModel>()

    @Inject
    lateinit var adapter: SubBreedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.setHasFixedSize(true)
        observeUIState()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.imageFav.setOnClickListener { viewModel.toggleFavourite() }
    }

    private fun observeUIState() {
        lifecycleScope.launch{
            viewModel.uiState.flowWithLifecycle(lifecycle).collect(::updateUI)
        }
        viewModel.isFavourite.observe(viewLifecycleOwner) {
            Log.d("subfragm", "isfav:$it")
            binding.imageFav.setImageResource(if (it) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
        }
    }

    fun updateUI(uiState: DogsSubBreedsViewModel.UiState) {
        when (uiState) {
            is DogsSubBreedsViewModel.UiState.Error -> {
                showNetworkError(viewModel::loadSubBreeds)
            }
            is DogsSubBreedsViewModel.UiState.Loading ->
                showProgress()
            is DogsSubBreedsViewModel.UiState.Success -> {
                if (uiState.cakes.isNotEmpty()) {
                    showContent()
                } else {
                    showEmptyStat("No Sub Breeds!")
                }
                adapter.submitList(uiState.cakes)
            }

        }
    }


}