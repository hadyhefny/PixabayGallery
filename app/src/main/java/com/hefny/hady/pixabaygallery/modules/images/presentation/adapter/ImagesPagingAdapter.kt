package com.hefny.hady.pixabaygallery.modules.images.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hefny.hady.pixabaygallery.databinding.ImageListItemBinding
import com.hefny.hady.pixabaygallery.modules.images.domain.entity.ImageEntity
import javax.inject.Inject


class ImagesPagingAdapter @Inject constructor() :
    PagingDataAdapter<ImageEntity, ImagesPagingAdapter.ImagesViewHolder>(DiffCallback) {

    var onHitClickListener: ((ImageEntity) -> Unit)? = null

    private object DiffCallback : DiffUtil.ItemCallback<ImageEntity>() {
        override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding =
            ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    inner class ImagesViewHolder(
        private val binding: ImageListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageEntity: ImageEntity) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(15))
            Glide.with(binding.root)
                .load(imageEntity.previewUrl)
                .apply(requestOptions)
                .into(binding.ivHit)
            binding.tvUserName.text = imageEntity.userName
            binding.tvTags.text = imageEntity.tags
            binding.root.setOnClickListener {
                onHitClickListener?.invoke(imageEntity)
            }
        }
    }
}