package com.snick.testvkapp.data.cloud

import com.google.gson.annotations.SerializedName
import com.snick.testvkapp.data.GifData


data class Response(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("meta")
    val meta: Meta
)


data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images,
    @SerializedName("import_datetime")
    val importDateTime: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
){

    fun map() = GifData(images.original.url,id, title, type)
}




data class Meta(
    @SerializedName("msg")
    val msg: String,
    @SerializedName("response_id")
    val responseId: String,
    @SerializedName("status")
    val status: Int
)


data class Images(
    @SerializedName("original")
    val original: Original,
)





data class Original(
    @SerializedName("frames")
    val frames: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("mp4")
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4_size: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("webp")
    val webp: String,
    @SerializedName("webp_size")
    val webp_size: String,
    @SerializedName("width")
    val width: String
)