package com.example.globo_challenger.presenter

import com.example.globo_challenger.model.Notice
import com.example.globo_challenger.service.NoticeService
import com.example.globo_challenger.service.RetrofitConnection
import com.example.globo_challenger.view.home.`interface`.HomeInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticePresenter(val view: HomeInterface.View?) : HomeInterface.Presenter {

    override fun getNotices() {
        view?.showProgressBar(true)
        val connection = RetrofitConnection().retrofit?.create(NoticeService::class.java)
        connection?.getNotice()?.enqueue(object : Callback<List<Notice>?> {
            override fun onResponse(call: Call<List<Notice>?>, response: Response<List<Notice>?>) {
                val notice = response.body()
                notice?.let {
                    view?.onSuccess(notice[0])
                }
                view?.showProgressBar(false)
            }

            override fun onFailure(call: Call<List<Notice>?>, t: Throwable) {
                view?.onError()
                view?.showProgressBar(false)
            }

        })
    }

}