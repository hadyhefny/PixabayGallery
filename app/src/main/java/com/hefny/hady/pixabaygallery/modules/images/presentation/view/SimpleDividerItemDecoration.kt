package com.hefny.hady.pixabaygallery.modules.images.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hefny.hady.pixabaygallery.R

class SimpleDividerItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable? = ContextCompat.getDrawable(context, R.drawable.divider)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left =
            parent.paddingLeft + context.resources.getDimension(R.dimen.item_divider_margin_horizontal)
                .toInt()
        val right =
            parent.width - (parent.paddingRight + context.resources.getDimension(R.dimen.item_divider_margin_horizontal)
                .toInt())
        val childCount = parent.adapter!!.itemCount
        for (i in 0 until childCount) {
            if (i == childCount - 1) {
                continue
            }
            val child: View? = parent.getChildAt(i)
            child?.let {
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider!!.intrinsicHeight
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }
}