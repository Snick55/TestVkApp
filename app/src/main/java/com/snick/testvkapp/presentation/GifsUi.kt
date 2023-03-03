package com.snick.testvkapp.presentation

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.snick.testvkapp.R
import com.snick.testvkapp.domain.GifDomain
import java.io.Serializable

interface GifsUi {
    class Loading : GifsUi
    data class Gifs(val gifs: List<GifUi>) : GifsUi

    data class Fail(private val message: String) : GifsUi {
        fun handle(textView: TextView) {
            textView.text = message
        }
    }

}

data class GifUi(
    val url: String,
    val id: String,
    val title: String,
    val type: String
) : Serializable {
    fun show(imageView: ImageView,isCentred: Boolean = true) {
       if (isCentred) {
           Glide.with(imageView.context)
               .asGif()
               .centerCrop()
               .placeholder(R.drawable.ic_placeholder)
               .load(url)
               .into(imageView)
       }else{
           Glide.with(imageView.context)
               .asGif()
               .placeholder(R.drawable.ic_placeholder)
               .load(url)
               .into(imageView)
       }
    }
}