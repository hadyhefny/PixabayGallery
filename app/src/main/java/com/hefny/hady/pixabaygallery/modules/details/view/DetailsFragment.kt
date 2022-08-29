package com.hefny.hady.pixabaygallery.modules.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hefny.hady.pixabaygallery.R
import com.hefny.hady.pixabaygallery.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        Glide.with(requireContext())
            .load(args.imageEntity.largeImageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivHit)
        binding.tvUserName.text = args.imageEntity.userName
        binding.tvTags.text = args.imageEntity.tags
        binding.tvLikes.text = args.imageEntity.noOfLikes.toString()
        binding.tvComment.text = args.imageEntity.noOfComments.toString()
        binding.tvDownload.text = args.imageEntity.noOfDownloads.toString()
    }
}