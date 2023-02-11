package com.example.mendofeelkotlin.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mendofeelkotlin.Adapters.RecyclerAdapterCategory
import com.example.mendofeelkotlin.Adapters.RecyclerAdapterCountry
import com.example.mendofeelkotlin.Adapters.RecyclerViewNews
import com.example.mendofeelkotlin.ModelClass.Article
import com.example.mendofeelkotlin.R
import com.example.mendofeelkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity(), RecyclerAdapterCategory.CellClickListener , RecyclerAdapterCountry.CellClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var categoryNames: List<String>
    private lateinit var countryNames: List<String>

    private var COUNTRY = "us"
    private var CATEGOTY = "business"
    private var SEARCH = ""
    private lateinit var URL : String

    private lateinit var recyclerviewnews : RecyclerView



    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        URL = "https://newsapi.org/v2/top-headlines?country="+COUNTRY+"&category="+CATEGOTY+ "&q="+SEARCH+"&apiKey=6f9b1b85277c4ccc89b3133bbe05b8d6"

        Log.d("yoyo", "COUNTRY : "+COUNTRY)

        Log.d("yoyo", "CATEGOTY : "+CATEGOTY)




        categoryNames = listOf("business","entertainment","general","health","science","sports","technology")

        countryNames = listOf("ae","ar","at","au","be","bg","br","ca","ch","cn","co","cu","cz","de","eg","fr","gb","gr","hk","hu","id","ie","il","in","it","jp","kr","lt","lv","ma","mx","my","ng","nl","no","nz","ph","pl","pt","ro","rs","ru","sa","se","sg","si","sk","th","tr","tw","ua","us","ve","za")

        val recyclerviewcategory = findViewById<RecyclerView>(R.id.categoryrecyclerView)

        val recyclerviewcountry = findViewById<RecyclerView>(R.id.countryrecyclerView)

        recyclerviewcategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerviewcountry.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val adapter1 = RecyclerAdapterCategory(categoryNames,this)
        val adapter2 = RecyclerAdapterCountry(countryNames,this)

        recyclerviewcategory.adapter = adapter1
        recyclerviewcountry.adapter = adapter2


        recyclerviewnews = findViewById<RecyclerView>(R.id.newsrecyclerview)
        recyclerviewnews.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)



        getdata()



        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                SEARCH = query.toString()
                getdata()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                SEARCH = newText.toString()
                getdata()
                return false
            }
        })






    }

    private fun getdata() {
        binding.progress.visibility = View.VISIBLE

        URL = "https://newsapi.org/v2/top-headlines?country="+COUNTRY+"&category="+CATEGOTY+ "&q="+SEARCH+"&apiKey=6f9b1b85277c4ccc89b3133bbe05b8d6"
        val queue = Volley.newRequestQueue(this)

        val getRequest: JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            Response.Listener {
                Log.e("sdsadas","$it")
                val newsJsonArray = it.getJSONArray("articles")
                val dataa = ArrayList<Article>()
                for(i in 0 until  newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = Article(
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("publishedAt"),
                        newsJsonObject.getString("title"),newsJsonObject.getString("url"),newsJsonObject.getString("urlToImage"),
                    )
                    //Toast.makeText(this,"Entered",Toast.LENGTH_LONG).show()
                    Log.d("jod", "getdata: "+news.title+"\n")

                    dataa.add(news)


                }
                val adapter3 = RecyclerViewNews(this,dataa)
                recyclerviewnews.adapter = adapter3
                Log.d("ada", "getdata: "+dataa)
                binding.progress.visibility = View.GONE
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

    override fun getcategory(data: String) {
        CATEGOTY =data
        getdata()
        Log.d("yoyo", "COUNTRY : "+COUNTRY)
        Log.d("yoyo", "CATEGOTY : "+CATEGOTY)

    }

    override fun getcountry(dataaa: String) {
        COUNTRY=dataaa
        getdata()
        Log.d("yoyo", "COUNTRY : "+COUNTRY)
        Log.d("yoyo", "CATEGOTY : "+CATEGOTY)

    }

}




