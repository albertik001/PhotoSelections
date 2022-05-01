package com.geektech.photoselection.data.model

import com.geektech.photoselection.base.BaseDiffModel

data class Picture(
    val images: String? = null,
    override val id: Int? = null
) : BaseDiffModel
