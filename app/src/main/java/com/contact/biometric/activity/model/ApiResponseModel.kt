package com.contact.biometric.activity.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponseModel {

    @SerializedName("total")
    @Expose
    private var total: Int? = null

    @SerializedName("totalHits")
    @Expose
    private var totalHits: Int? = null

    @SerializedName("hits")
    @Expose
    private var hits: List<Hit?>? = null

    fun getTotal(): Int? {
        return total
    }

    fun setTotal(total: Int?) {
        this.total = total
    }

    fun getTotalHits(): Int? {
        return totalHits
    }

    fun setTotalHits(totalHits: Int?) {
        this.totalHits = totalHits
    }

    fun getHits(): List<Hit?>? {
        return hits
    }

    fun setHits(hits: List<Hit?>?) {
        this.hits = hits
    }

    class Hit {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("pageURL")
        @Expose
        var pageURL: String? = null

        @SerializedName("type")
        @Expose
        var type: String? = null

        @SerializedName("tags")
        @Expose
        var tags: String? = null

        @SerializedName("previewURL")
        @Expose
        var previewURL: String? = null

        @SerializedName("previewWidth")
        @Expose
        var previewWidth: Int? = null

        @SerializedName("previewHeight")
        @Expose
        var previewHeight: Int? = null

        @SerializedName("webformatURL")
        @Expose
        var webformatURL: String? = null

        @SerializedName("webformatWidth")
        @Expose
        var webformatWidth: Int? = null

        @SerializedName("webformatHeight")
        @Expose
        var webformatHeight: Int? = null

        @SerializedName("largeImageURL")
        @Expose
        var largeImageURL: String? = null

        @SerializedName("imageWidth")
        @Expose
        var imageWidth: Int? = null

        @SerializedName("imageHeight")
        @Expose
        var imageHeight: Int? = null

        @SerializedName("imageSize")
        @Expose
        var imageSize: Int? = null

        @SerializedName("views")
        @Expose
        var views: Int? = null

        @SerializedName("downloads")
        @Expose
        var downloads: Int? = null

        @SerializedName("favorites")
        @Expose
        var favorites: Int? = null

        @SerializedName("likes")
        @Expose
        var likes: Int? = null

        @SerializedName("comments")
        @Expose
        var comments: Int? = null

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null

        @SerializedName("user")
        @Expose
        var user: String? = null

        @SerializedName("userImageURL")
        @Expose
        var userImageURL: String? = null
    }
}