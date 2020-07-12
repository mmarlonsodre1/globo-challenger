package com.example.globo_challenger.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.globo_challenger.R
import com.example.globo_challenger.util.loadFromUrl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildNotice()
        args.notice.url?.let {
            (activity as MainActivity).toolbar?.menu?.getItem(0)?.apply {
                isVisible = true
                setOnMenuItemClickListener {
                    shareText()
                    true
                }
            }
        }
    }

    private fun buildNotice() {
        tv_title?.text = args.notice.titulo
        tv_subtitle?.text = args.notice.subTitulo

        if (!args.notice.autores.isNullOrEmpty()) tv_publisher?.text = args.notice.autores?.get(0)
        else ll_publisher?.isVisible = false

        tv_datetime?.text = formatterDateTime(args.notice.publicadoEm)
        if (!args.notice.imagens.isNullOrEmpty()) {
            args.notice.imagens?.get(0)?.url?.let { iv_notice?.loadFromUrl(it) }
            tv_image_title?.text = getString(
                R.string.detail_image_legend,
                args.notice.imagens?.get(0)?.legenda,
                args.notice.imagens?.get(0)?.fonte
            )
        } else {
            iv_notice?.isVisible = false
            tv_image_title?.isVisible = false
        }

        tv_message?.text = args.notice.texto
    }

    private fun shareText() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, args.notice.url)
        startActivity(Intent.createChooser(intent, getString(R.string.shared_notice)))
    }

    private fun formatterDateTime(dateTimeUnformatter: String?) : String {
        val datetime = LocalDateTime.parse(dateTimeUnformatter,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX"))

        return datetime.format(DateTimeFormatter.ofPattern("dd/mm/yy HH:mm",
            Locale("pt", "BR")
        ))
    }
}