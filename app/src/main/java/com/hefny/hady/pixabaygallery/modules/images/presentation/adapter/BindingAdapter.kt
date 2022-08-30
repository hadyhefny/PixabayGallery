package com.hefny.hady.pixabaygallery.modules.images.presentation.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hefny.hady.pixabaygallery.modules.images.presentation.view.SimpleDividerItemDecoration

@BindingAdapter(value = ["url", "hitPlaceholder"], requireAll = false)
fun loadHit(ivHit: ImageView, previewUrl: String?, placeHolder: Drawable?) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(15))
    Glide.with(ivHit)
        .load(previewUrl)
        .placeholder(placeHolder)
        .apply(requestOptions)
        .into(ivHit)
}

@BindingAdapter("itemDecoration")
fun addItemDecorator(recyclerView: RecyclerView, itemDecoration: SimpleDividerItemDecoration?) {
    itemDecoration?.let { recyclerView.addItemDecoration(it) }
}