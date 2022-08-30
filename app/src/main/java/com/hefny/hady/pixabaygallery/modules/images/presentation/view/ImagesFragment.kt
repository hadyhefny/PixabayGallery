package com.hefny.hady.pixabaygallery.modules.images.presentation.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.hefny.hady.pixabaygallery.R
import com.hefny.hady.pixabaygallery.core.extensions.parseError
import com.hefny.hady.pixabaygallery.databinding.FragmentImagesBinding
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import com.hefny.hady.pixabaygallery.modules.images.presentation.ImagesViewModel
import com.hefny.hady.pixabaygallery.modules.images.presentation.adapter.ImagesLoadStateAdapter
import com.hefny.hady.pixabaygallery.modules.images.presentation.adapter.ImagesPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding

    @Inject
    lateinit var imagesPagingAdapter: ImagesPagingAdapter

    @Inject
    lateinit var imagesLoadStateAdapter: ImagesLoadStateAdapter
    private val imagesViewModel: ImagesViewModel by viewModels()
    private val itemDecorator by lazy { SimpleDividerItemDecoration(requireContext()) }
    private lateinit var searchView: SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        binding.pagingAdapter = imagesPagingAdapter
        binding.loadAdapter = imagesLoadStateAdapter
        binding.itemDecorator = itemDecorator
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservation()
        initListeners()
        initMenu()
    }

    private fun initMenu() {
        val menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val menuItem: MenuItem = menu.findItem(R.id.search)
                searchView = menuItem.actionView as SearchView
                if (imagesViewModel.isActionViewExpanded) {
                    menuItem.expandActionView()
                    searchView.clearFocus()
                } else {
                    menuItem.collapseActionView()
                }
                menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                        imagesViewModel.isActionViewExpanded = true
                        return true
                    }

                    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                        imagesViewModel.isActionViewExpanded = false
                        return true
                    }
                })
                searchView.setQuery(imagesViewModel.searchQuery, false)
                searchView.maxWidth = Int.MAX_VALUE
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (!query.isNullOrBlank()) {
                            imagesViewModel.getImages(query)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner)
    }

    private fun initListeners() {
        imagesPagingAdapter.onHitClickListener = {
            showConfirmationDialog(it)
        }
        imagesPagingAdapter.addLoadStateListener {
            binding.layoutLoading.clLoading.isVisible = it.refresh is LoadState.Loading
            if (it.refresh is LoadState.Error && imagesPagingAdapter.snapshot().isEmpty())
                binding.layoutError.tvError.text =
                    (it.refresh as LoadState.Error).error.parseError().message
            binding.layoutError.clLoading.isVisible =
                it.refresh is LoadState.Error && imagesPagingAdapter.snapshot().isEmpty()
        }
        binding.layoutError.btnRetry.setOnClickListener {
            imagesPagingAdapter.retry()
        }
        imagesLoadStateAdapter.onRetyButonClickListener = {
            imagesPagingAdapter.retry()
        }
    }

    private fun showConfirmationDialog(imageEntity: ImageEntity) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.confirmation_dialog_msg))
            .setPositiveButton(
                getString(R.string.ok)
            ) { _, _ ->
                val action =
                    ImagesFragmentDirections.actionImagesFragmentToDetailsFragment(imageEntity)
                findNavController().navigate(action)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
            .show()
    }

    private fun initObservation() {
        imagesViewModel.imagesStateLiveData.observe(viewLifecycleOwner) { imagesState ->
            imagesState.data?.let { imagesPagingData ->
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    imagesPagingAdapter.submitData(imagesPagingData)
                }
            }
        }
    }
}