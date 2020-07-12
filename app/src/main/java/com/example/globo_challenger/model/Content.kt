package com.example.globo_challenger.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Content (
    @SerializedName("autores") val autores: List<String>? = null,
    @SerializedName("informePublicitario") val informePublicitario: Boolean? = null,
    @SerializedName("subTitulo") val subTitulo: String? = null,
    @SerializedName("texto") val texto: String? = null,
    @SerializedName("videos") val videos: List<String>? = null,
    @SerializedName("atualizadoEm") val atualizadoEm: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("publicadoEm") val publicadoEm: String? = null,
    @SerializedName("secao") val secao: Session? = null,
    @SerializedName("tipo") val tipo: String? = null,
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("urlOriginal") val urlOriginal: String? = null,
    @SerializedName("imagens") val imagens: List<ImageNotice>? = null
) : Serializable