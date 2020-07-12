package com.example.globo_challenger.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Session (
    @SerializedName("nome") val nome: String? = null,
    @SerializedName("url") val url: String? = null
) : Serializable