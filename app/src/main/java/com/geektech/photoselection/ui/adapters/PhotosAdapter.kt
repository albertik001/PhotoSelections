package com.geektech.photoselection.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geektech.photoselection.base.BaseDiffUtil
import com.geektech.photoselection.common.extensions.loadWithGlide
import com.geektech.photoselection.data.model.Picture
import com.geektech.photoselection.databinding.ItemImageBinding

class PhotosAdapter : ListAdapter<Picture, PhotosAdapter.PhotosViewHolder>(BaseDiffUtil()) {

    val selectedList = arrayListOf<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class PhotosViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            picture.images?.let { it -> binding.imageView.loadWithGlide(it) }
            binding.root.setOnClickListener {
                if (binding.selectionImage.isInvisible) {
                    binding.selectionImage.isVisible = true
                    selected(picture.images)
                } else {
                    binding.selectionImage.isInvisible = true
                    isSelected(picture.images)
                }
            }
        }

        private fun isSelected(imageView: String?) {
            selectedList.remove(Picture(images = imageView))
        }

        private fun selected(imageView: String?) {
            selectedList.add(Picture(images = imageView))
        }
    }
}
