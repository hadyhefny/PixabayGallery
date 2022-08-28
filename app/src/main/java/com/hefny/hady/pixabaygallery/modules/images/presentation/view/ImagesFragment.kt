package com.hefny.hady.pixabaygallery.modules.images.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hefny.hady.pixabaygallery.databinding.FragmentImagesBinding
import com.hefny.hady.pixabaygallery.modules.images.presentation.ImagesViewModel
import com.hefny.hady.pixabaygallery.modules.images.presentation.adapter.ImagesLoadStateAdapter
import com.hefny.hady.pixabaygallery.modules.images.presentation.adapter.ImagesPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding

    private val TAG = "AppDebug"

    @Inject
    lateinit var imagesPagingAdapter: ImagesPagingAdapter

    @Inject
    lateinit var imagesLoadStateAdapter: ImagesLoadStateAdapter
    private val imagesViewModel: ImagesViewModel by viewModels()
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
    }

    private fun initRecyclerView() {
        with(binding.rvImages) {
            setHasFixedSize(true)
            adapter = imagesPagingAdapter.withLoadStateFooter(imagesLoadStateAdapter)
        }
    }

    private fun initObservation() {
        imagesViewModel.imagesStateLiveData.observe(viewLifecycleOwner) { imagesState ->
            imagesState.data?.let { imagesPagingData ->
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    imagesPagingAdapter.submitData(imagesPagingData)
                    Log.d(TAG, "initObservation: ${imagesPagingAdapter.itemCount}")
                }
            }
        }
    }
}