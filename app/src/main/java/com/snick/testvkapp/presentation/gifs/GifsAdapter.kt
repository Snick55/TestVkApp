package com.snick.testvkapp.presentation.gifs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snick.testvkapp.databinding.ItemGifBinding
import com.snick.testvkapp.presentation.GifUi


class GifsAdapter(private val listener: Listener) :
    RecyclerView.Adapter<GifsAdapter.MyViewHolder>(), View.OnClickListener {

    private var gifs: List<GifUi> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUp(list: List<GifUi>) {
        gifs = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGifBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(gifs[position])
    }

    override fun getItemCount(): Int = gifs.size

    override fun onClick(view: View) {
        val gif = view.tag as GifUi
        listener.handle(gif)
    }

    class MyViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ui: GifUi) {
            binding.root.tag = ui
            ui.show(binding.gifImageview)
        }
    }

    interface Listener {

        fun handle(gif: GifUi)
    }

}


