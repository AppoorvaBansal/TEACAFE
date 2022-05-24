package com.google.developers.teacup.ui.detail

import com.google.developers.teacup.R
import com.google.developers.teacup.data.TeaType

/**
 * Ui model for TeaDetailActivity.
 */
class TeaDetailModel(private val teaType: String) {

    val imageId: Int
        get() = getTeaImage(teaType)

    /**
     * Supported background toolbar images
     *
     * @param type of tea e.g. "Black tea"
     * @return resource identifier for drawable
     */
    private fun getTeaImage(type: String): Int {
        return when (type) {
            TeaType.GREEN_TEA.value -> R.drawable.green_tea
            TeaType.HERBAL_TEA.value -> R.drawable.herbal_tea
            TeaType.BLACK_TEA.value -> R.drawable.black_tea
            else -> R.drawable.black_tea
        }
    }
}
