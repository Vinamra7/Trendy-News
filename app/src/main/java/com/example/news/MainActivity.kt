package com.example.news

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.news.MySingleton

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val idd =  findViewById<RecyclerView>(R.id.recyclerView)
        idd.layoutManager = LinearLayoutManager(this)
        fetchData()
        mAdapter = Adapter(this)
        idd.adapter = mAdapter

    }
    fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=5fecd910cb6543b0988b04e37c4ce41a"
        val getRequest: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                Log.e("sdsadas","$it")
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<newss>()
                for(i in 0 until  newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = newss(
                        newsJsonObject.getString("author").toString(),
                        newsJsonObject.getString("title").toString(),
                        newsJsonObject.getString("url").toString(),
                        newsJsonObject.getString("urlToImage").toString()
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener { error ->

            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "Mozilla/5.0"
                return params
            }
        }
        queue.add(getRequest)
    }

    override fun onItemClicked(item: newss) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}
/*
*
*
* */
/*
*
* */