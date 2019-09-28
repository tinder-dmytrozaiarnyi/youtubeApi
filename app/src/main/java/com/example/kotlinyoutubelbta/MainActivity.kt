package com.example.kotlinyoutubelbta

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView.setBackgroundColor(Color.GRAY)

        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to fetch JSON")
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"

        val request = Request.Builder()
            .url(url)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread{
                    recyclerView.adapter = MainAdapter(homeFeed)

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println(" Failed To Execute Request")
            }
        })
    }
}


class HomeFeed(val videos: List<Video>)

class Video(
    val id: Int, val name: String,
    val link: String, val imageUrl: String,
    val numberOfViews: Int, val channel: Channel
)

class Channel(val name: String, val profileImageUrl: String)