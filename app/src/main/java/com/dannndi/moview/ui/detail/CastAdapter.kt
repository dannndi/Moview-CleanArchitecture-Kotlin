package com.dannndi.moview.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dannndi.moview.core.domain.model.Cast
import com.dannndi.moview.databinding.ItemCastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val cast = mutableListOf<Cast>()

    fun setList(castList: List<Cast>) {
        cast.clear()
        cast.addAll(castList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int = cast.size

    class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w154${cast.profilePath}")
                    .into(imgCast)
                tvCastName.text = cast.name
                tvCharacterName.text = cast.character
            }
        }
    }
}