package com.androiddemo.ui.traffic

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddemo.R
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.androiddemo.adapter.MoviesAdapter
import com.androiddemo.adapter.TrafficAdapter
import com.androiddemo.model.Movies
import com.androiddemo.model.Traffic
import com.androiddemo.utils.Constants
import com.androiddemo.utils.DialogBox
import com.androiddemo.utils.Utils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_traffic.*
import org.json.JSONException


class TrafficActivity : AppCompatActivity() {

    private var requestQueue: RequestQueue? = null

    private var context: Context = this
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var arrayListTraffic = ArrayList<Traffic.Feature>()
    private lateinit var trafficAdapter: TrafficAdapter
    private lateinit var rvTraffic: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)

        title = resources.getString(R.string.seattle_traffic_cameras)

        setRecyclerView()

        requestQueue = Volley.newRequestQueue(this)

        if(!Utils.isNetworkAvailable(context)) {
            DialogBox.dialogNoInternet(context)
        }
        else {
            sendAndRequestResponse()
        }
    }

    private fun setRecyclerView() {
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvTraffic = findViewById(R.id.rvTraffic)
        rvTraffic.layoutManager = linearLayoutManager
        rvTraffic.itemAnimator = DefaultItemAnimator()
    }


    private fun sendAndRequestResponse() {
        val request = JsonObjectRequest(Request.Method.GET, Constants.API_URL, null, { response ->
            try {

                progressBar.visibility = View.GONE
                val successResponseModel  = Gson().fromJson(response.toString(), Traffic::class.java)

                arrayListTraffic = successResponseModel?.Features!!
                trafficAdapter = TrafficAdapter(context, arrayListTraffic)
                rvTraffic.adapter = trafficAdapter

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            error.printStackTrace()
        })
        requestQueue?.add(request)
    }

}