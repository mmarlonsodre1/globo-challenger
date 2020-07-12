package com.example.globo_challenger.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globo_challenger.R
import com.example.globo_challenger.model.Content
import com.example.globo_challenger.util.loadFromUrl
import com.example.globo_challenger.view.MainView
import kotlinx.android.synthetic.main.adapter_home.view.*

class HomeAdapter(
    private val content: List<Content>,
    private val listener: MainView
): RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        return HomeAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false))
    }

    override fun getItemCount(): Int = content.size

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(content[position], listener)
    }

    class HomeAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Content, listener: MainView) = with(itemView) {
            if (!item.imagens.isNullOrEmpty()) item.imagens[0].url?.let { iv_notice?.loadFromUrl(it) }
            tv_publisher?.text = item.secao?.nome
            tv_title?.text = item.titulo
            this.setOnClickListener { listener.goDetail(item) }
        }
    }
}