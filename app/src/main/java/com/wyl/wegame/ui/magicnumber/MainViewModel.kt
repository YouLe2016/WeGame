package com.wyl.wegame.ui.magicnumber

import androidx.lifecycle.ViewModel
import com.wyl.wegame.R

class MainViewModel : ViewModel() {
    private val dataList = mutableListOf(
        1 to R.drawable.magic_card1,
        2 to R.drawable.magic_card2,
        4 to R.drawable.magic_card4,
        8 to R.drawable.magic_card8,
        16 to R.drawable.magic_card16,
        32 to R.drawable.magic_card32,
        64 to R.drawable.magic_card64
    ).apply {
        this.shuffle()
    }

    var index = 0

    private var number = 0

    fun data() = dataList[index]

    fun addNumber() {
        if (index >= dataList.lastIndex) return
        number += dataList[index].first
    }

    fun hasNextPage(): Boolean = index++ < dataList.lastIndex

    fun answer() =
        if (number <= 100) {
            number.toString()
        } else {
            "哼哼, \n别以为我不知道, \n你骗我了..."
        }

    fun reset() {
        index = 0
        number = 0
    }
}
