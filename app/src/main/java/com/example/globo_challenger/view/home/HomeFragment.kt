package com.example.globo_challenger.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globo_challenger.R
import com.example.globo_challenger.model.Content
import com.example.globo_challenger.model.Notice
import com.example.globo_challenger.presenter.NoticePresenter
import com.example.globo_challenger.util.loadFromUrl
import com.example.globo_challenger.view.MainActivity
import com.example.globo_challenger.view.MainView
import com.example.globo_challenger.view.home.`interface`.HomeInterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeInterface.View {

    private val presenter = NoticePresenter(this)
    private var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getNotices()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (fragmentView != null) return fragmentView

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        fragmentView = view

        btn_try_again?.setOnClickListener { presenter.getNotices() }
        return view
    }

    private fun buildTopNotice(content: Content?) {
        content?.let {
            if (!it.imagens.isNullOrEmpty())
            it.imagens[0].url?.let { url ->
                iv_top_image?.loadFromUrl(url)
                iv_session_name?.text = it.secao?.nome
                tv_image_title?.text = it.titulo
                cl_first_notice?.setOnClickListener { (activity as MainActivity).goDetail(content) }
            } ?: run {
                cl_first_notice.isVisible = false
            }
        }
    }

    private fun buildList(notices: List<Content>?) {
        notices?.toMutableList()?.removeAt(0)
        notices?.let {
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rl_notices?.layoutManager = layoutManager
            rl_notices?.isNestedScrollingEnabled = false

            val dividerItemDecoration =  DividerItemDecoration(
                rl_notices.context,
                layoutManager.orientation
            )
            rl_notices.addItemDecoration(dividerItemDecoration)
            rl_notices?.adapter = HomeAdapter(notices, (activity as MainView))
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).toolbar?.menu?.getItem(0)?.isVisible = false
    }

    override fun showProgressBar(value: Boolean) {
        progress_bar?.isVisible = value
    }

    override fun onSuccess(notice: Notice) {
        val filterNotices = notice.conteudos?.filter { content -> content.tipo == "materia" }
        buildTopNotice(filterNotices?.get(0))
        buildList(filterNotices)

        cl_error_notices?.isVisible = false
        cl_sucess_notices.isVisible = true
    }

    override fun onError() {
        cl_error_notices?.isVisible = true
        cl_sucess_notices.isVisible = false
    }
}