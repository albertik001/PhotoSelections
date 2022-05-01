package com.geektech.photoselection.ui.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.photoselection.R
import com.geektech.photoselection.base.BaseFragment
import com.geektech.photoselection.common.extensions.navigateSafely
import com.geektech.photoselection.common.extensions.submitData
import com.geektech.photoselection.data.model.Picture
import com.geektech.photoselection.databinding.FragmentPhotosBinding
import com.geektech.photoselection.ui.adapters.PhotosAdapter

class PhotosFragment : BaseFragment<FragmentPhotosBinding>(R.layout.fragment_photos) {

    override val binding by viewBinding(FragmentPhotosBinding::bind)
    private val photoAdapter = PhotosAdapter()
    private val viewModel: PhotosViewModel by activityViewModels()
    private val list = arrayListOf<Picture>()

    private fun addPicture(): List<Picture> {
        list.apply {
            add(Picture(images = "https://clck.ru/ga7Eu"))
            add(Picture(images = "https://clck.ru/ga7Jw"))
            add(Picture(images = "https://clck.ru/ga7MC"))
            add(Picture(images = "https://clck.ru/ga7Eu"))
            add(Picture(images = "https://clck.ru/ga7Eu"))
            add(Picture(images = "https://clck.ru/ga7Eu"))
        }
        return list
    }

    override fun initViews() {
        setupAdapter()
        photoAdapter.submitData(addPicture())
    }

    private fun setupAdapter() {
        binding.recyclerview.adapter = photoAdapter
        binding.recyclerview.layoutManager = GridLayoutManager(context, 3)
    }

    override fun initListeners() {
        binding.btnNext.setOnClickListener {
            viewModel.putPicture(photoAdapter.selectedList)
            findNavController().navigateSafely(R.id.action_photosFragment_to_selectedPhotoFragment)
        }
    }
}