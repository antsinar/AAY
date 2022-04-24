package com.example.cookit.network

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("ing0")
    val ing0: String,
    @SerializedName("ing1")
    val ing1: String,
    @SerializedName("ing2")
    val ing2: String,
    @SerializedName("ing3")
    val ing3: String,
    @SerializedName("ing4")
    val ing4: String,
    @SerializedName("ing5")
    val ing5: String,
    @SerializedName("ing6")
    val ing6: String,
    @SerializedName("ing7")
    val ing7: String,
    @SerializedName("ing8")
    val ing8: String,
    @SerializedName("ing9")
    val ing9: String,
    @SerializedName("ing10")
    val ing10: String,
    @SerializedName("ing11")
    val ing11: String,
    @SerializedName("ing12")
    val ing12: String,
    @SerializedName("ing13")
    val ing13: String,
    @SerializedName("ing14")
    val ing14: String,
    @SerializedName("ing15")
    val ing15: String,
    @SerializedName("ing16")
    val ing16: String,
    @SerializedName("ing17")
    val ing17: String,
    @SerializedName("ing18")
    val ing18: String,
    @SerializedName("ing19")
    val ing19: String,
    @SerializedName("ing20")
    val ing20: String,
    @SerializedName("step0")
    val step0: String,
    @SerializedName("step1")
    val step1: String,
    @SerializedName("step2")
    val step2: String,
    @SerializedName("step3")
    val step3: String,
    @SerializedName("step4")
    val step4: String,
    @SerializedName("step5")
    val step5: String,
    @SerializedName("step6")
    val step6: String,
    @SerializedName("step7")
    val step7: String,
    @SerializedName("step8")
    val step8: String,
    @SerializedName("step9")
    val step9: String,
    )

