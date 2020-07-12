package com.example.globo_challenger.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageNotice (
    @SerializedName("autor") val autor: String? = null,
    @SerializedName("fonte") val fonte: String? = null,
    @SerializedName("legenda") val legenda: String? = null,
    @SerializedName("url") val url: String? = null
) : Serializable