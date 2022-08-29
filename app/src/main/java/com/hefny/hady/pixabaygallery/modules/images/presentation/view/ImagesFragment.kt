package com.hefny.hady.pixabaygallery.modules.images.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hefny.hady.pixabaygallery.R
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservation()
        initListeners()
    }

    private fun initListeners() {
        imagesPagingAdapter.onHitClickListener = {
            showConfirmationDialog(it)
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

    private fun initRecyclerView() {
        with(binding.rvImages) {
            setHasFixedSize(true)
            adapter = imagesPagingAdapter.withLoadStateFooter(imagesLoadStateAdapter)
            addItemDecoration(itemDecorator)
        }
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