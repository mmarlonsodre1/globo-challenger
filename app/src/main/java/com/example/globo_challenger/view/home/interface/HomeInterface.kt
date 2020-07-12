package com.example.globo_challenger.view.home.`interface`

import com.example.globo_challenger.model.Notice

interface HomeInterface {
    interface Presenter {
        fun getNotices()
    }

    interface View {
        fun showProgressBar(value: Boolean)
        fun onSuccess(notice: Notice)
        fun onError()
    }
}