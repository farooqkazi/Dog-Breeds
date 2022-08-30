package com.assignment.dogbreeds.application.presentation.fragments.favourite

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
import com.assignment.dogbreeds.databinding.FragmentDogsFavouriteListBinding
import com.assignment.dogbreeds.databinding.FragmentDogsSubBreedsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_dogs_favourite_list),
    EmptyStatePresenter {
    override fun getEmptyStateLayout() = binding.emptyLayout
    private val binding by viewBinding<FragmentDogsFavouriteListBinding>()
    private val viewModel by viewModels<FavouritesViewModel>()

    @Inject
    lateinit var adapter: FavouriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.recyclerView.setHasFixedSize(true)
        observeUIState()
        adapter.setOnFavBreedClickListener {
            viewModel.toggleFavourite(it)
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeUIState() = lifecycleScope.launch {
        viewModel.favourites.flowWithLifecycle(lifecycle).collect(::updateUI)
    }

    private fun updateUI(breeds: List<Breed>) {
        if (breeds.isNotEmpty()) {
            showContent()
        } else {
            showEmptyStat("No Favourites!")
        }
        adapter.submitList(breeds)
    }


}