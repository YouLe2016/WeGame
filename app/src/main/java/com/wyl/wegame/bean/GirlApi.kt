package com.wyl.wegame.bean

data class GirlApi(
    val error: Boolean,
    var results: List<GirlItem>
)

data class GirlItem(
    val _id: String,
    val desc: String,
    val url: String,
    val who: String
)
