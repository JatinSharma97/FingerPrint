package com.contact.biometric.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.contact.biometric.R
import com.contact.biometric.activity.model.ApiResponseModel

class MyAdapter(var context: Context, list: List<ApiResponseModel.Hit>, select: Select) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var list: List<ApiResponseModel.Hit>
    var select: Select

    interface Select {
        fun onSelected(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_images, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].webformatURL).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView

        init {
            image = itemView.findViewById(R.id.myimage)
        }
    }

    init {
        this.list = list
        this.select = select
    }
}