package com.wyl.wegame.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class GirlApi(
    val error: Boolean,
    var results: List<GirlItem>
)


@Parcelize
data class GirlItem(
    val _id: String,
    val desc: String,
    val url: String,
    val who: String
) : Parcelable
