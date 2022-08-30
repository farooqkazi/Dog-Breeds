package com.assignment.dogbreeds.application.presentation.fragments.breedList

import android.os.Bundle
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
import com.assignment.dogbreeds.application.presentation.uimodels.Breed
import com.assignment.dogbreeds.databinding.FragmentDogsBreedsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DogsBreedsListFragment : Fragment(R.layout.fragment_dogs_breeds_list), (Breed) -> Unit,
    EmptyStatePresenter {
    override fun getEmptyStateLayout() = binding.emptyLayout
    private val binding by viewBinding<FragmentDogsBreedsListBinding>()
    private val viewModel by viewModels<DogsBreedsListViewModel>()

    @Inject
    lateinit var adapter: DogBreedRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.setHasFixedSize(true)
        observeUIState()
        binding.fabFavourite.setOnClickListener {
            val direction =
                DogsBreedsListFragmentDirections.actionNavBreedsFragmentToNavFavouriteFragment()
            findNavController().navigate(direction)
        }
        adapter.setOnBreedClickListener(this)
    }

    private fun observeUIState() = lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect(::updateUI)
    }

    fun updateUI(uiState: DogsBreedsListViewModel.UiState) {
        when (uiState) {
            is DogsBreedsListViewModel.UiState.Error -> {
                showNetworkError(viewModel::loadBreeds)
            }
            is DogsBreedsListViewModel.UiState.Loading ->
                showProgress()
            is DogsBreedsListViewModel.UiState.Success -> {
                if (uiState.cakes.isNotEmpty()) {
                    showContent()
                } else {
                    showEmptyStat("No Breeds data available right now!")
                }
                adapter.submitList(uiState.cakes)
            }

        }
    }

    override fun invoke(breed: Breed) {
        val direction =
            DogsBreedsListFragmentDirections.actionNavBreedsFragmentToNavSubBreedsFragment(breed)
        findNavController().navigate(direction)
    }


}