package com.example.globo_challenger.service

import com.example.globo_challenger.model.Notice
import retrofit2.Call
import retrofit2.http.GET

interface NoticeService {
    @GET("capa.json")
        fun getNotice(): Call<List<Notice>?>?
}