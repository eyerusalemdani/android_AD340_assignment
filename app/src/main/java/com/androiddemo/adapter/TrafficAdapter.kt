package com.androiddemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddemo.R
import com.androiddemo.model.Traffic
import com.androiddemo.ui.map.MapsActivity
import com.androiddemo.ui.movies.MovieDetailsActivity
import com.androiddemo.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.item_traffic.view.*

class TrafficAdapter(
    private val context: Context,
    private val mValues: ArrayList<Traffic.Feature>
) : RecyclerView.Adapter<TrafficAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_traffic, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.mView.description.text = item.Cameras[0].Description

        val image = Constants.BASE_URL_IMAGE + item.Cameras[0].ImageUrl

        val requestOptions = RequestOptions()
        requestOptions.error(R.color.grey)
        Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
            .load(image)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    if (holder.mView.progressBar.visibility == View.VISIBLE) {
                        holder.mView.progressBar.visibility = View.GONE
                    }
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any, target: Target<Drawable?>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    if (holder.mView.progressBar.visibility == View.VISIBLE) {
                        holder.mView.progressBar.visibility = View.GONE
                    }
                    return false
                }
            })
            .into(holder.mView.image)

        holder.mView.relativeLayout.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra(Constants.DATA, mValues)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        override fun toString(): String {
            return super.toString()
        }
    }

}