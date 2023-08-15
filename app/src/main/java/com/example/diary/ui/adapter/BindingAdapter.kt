package com.example.diary.ui.adapter

import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("setImage")
fun loadImage(view: ImageView, imageUrl: Int) {
    if (imageUrl == 1) return
    view.setImageResource(imageUrl)
}

@BindingAdapter("dynamicWidth")
fun setDynamicWidth(cardView: CardView, widthDp: Float) {
    val widthPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        widthDp,
        cardView.resources.displayMetrics
    )
    val layoutParams = cardView.layoutParams
    layoutParams.width = widthPx.toInt()
    cardView.layoutParams = layoutParams
}

@BindingAdapter("dynamicHeight")
fun setDynamicHeight(cardView: CardView, heightDp: Float) {
    val heightPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        heightDp,
        cardView.resources.displayMetrics
    )
    val layoutParams = cardView.layoutParams
    layoutParams.height = heightPx.toInt()
    cardView.layoutParams = layoutParams
}

@BindingAdapter("customTopConstraint")
fun setCustomTopConstraint(view: View, targetId: Int) {
    val parent = view.parent as? ConstraintLayout ?: return
    val constraintSet = ConstraintSet()
    constraintSet.clone(parent)
    constraintSet.connect(view.id, ConstraintSet.TOP, targetId, ConstraintSet.BOTTOM)
    constraintSet.applyTo(parent)
}

@BindingAdapter("customLayoutHeight")
fun setCustomLayoutHeight(view: View, height: Float) {
    val layoutParams = view.layoutParams
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("customMarginBottom")
fun setCustomMarginBottom(view: View, marginBottom: Float) {
    if (view.layoutParams is MarginLayoutParams) {
        val p = view.layoutParams as MarginLayoutParams
        p.setMargins(p.leftMargin, p.topMargin, p.rightMargin, marginBottom.toInt())
        view.requestLayout()
    }
}