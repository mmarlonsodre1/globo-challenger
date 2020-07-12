package com.example.globo_challenger.model

import com.google.gson.annotations.SerializedName

class Notice (
    @SerializedName("conteudos") val conteudos: List<Content>? = null,
    @SerializedName("produto") val produto: String? = null
)