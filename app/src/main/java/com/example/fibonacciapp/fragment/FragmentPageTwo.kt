package com.example.fibonacciapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.room.Room

import com.example.fibonacciapp.R
import kotlinx.android.synthetic.main.fragment_page_two.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fibonacciapp.JsonPlaceHolderApi
import com.example.fibonacciapp.MainActivity
import com.example.fibonacciapp.db.enitity.AppDatabase
import com.example.fibonacciapp.db.enitity.App_Entity
import com.example.fibonacciapp.db.enitity.LOCATION_TABLE_ID
import com.example.fibonacciapp.db.enitity.Location
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentPageTwo : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  arguments?.let {
        //      param1 = it.getString(key)
        // }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-0c3260-ropa.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        var call = jsonPlaceHolderApi.locations

        call.enqueue(object : Callback<List<Location>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                if (!response.isSuccessful) {
                  //  titleView?.text = "Code: " + response.code()
                }

                var locations: List<Location>? = response.body()

                var firstElement = locations?.get(0)

                var firstImage = firstElement?.pictures?.get(0)

                titleView.text = firstElement?.title

                nameView.text = firstElement?.name

                Picasso.get()
                    .load(firstImage)
                    .into(imageView)
                Thread {
                    db.appDAO().saveLocation(
                        Location(
                            0,
                            firstElement?.title,
                            firstElement?.name,
                            mutableListOf(firstImage!!)
                        )
                    )
                    db.appDAO().getLocation().forEach {
                        Log.i("TAG", "$it")
                    }
                }.start()
            }

            var db =
                Room.databaseBuilder(activity as MainActivity, AppDatabase::class.java, "AppDB").allowMainThreadQueries()
                    .build()

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                t.printStackTrace()

                var savedLocations = db.appDAO().getLocation()
                var imageElm = savedLocations[0].pictures?.get(0)
                titleView.text = savedLocations[0].title
                nameView.text = savedLocations[0].name
                if (imageElm.isNullOrEmpty()) {
                    Log.i("ERROR", "img is null img is null")
                } else {
                    Picasso.get()
                        .load(imageElm)
                        .into(imageView)
                }
            }

        })


        /*Thread {

            var db =
                Room.databaseBuilder(activity as MainActivity, AppDatabase::class.java, "AppDB")
                    .build()

            db.appDAO().saveLocation(Location(0, "Titlul", "Numele", mutableListOf()))


            db.appDAO().getLocation().forEach {
                Log.i("@BLABLA", "$it")
            }
        }.start() */
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page_two, container, false)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun setUp() {
    }

    private fun getUrl(id: Int): String {
        val id = (0..101).random()
        return "https://picsum.photos/id/$id/200"
    }

    private val key = "key"

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentPageTwo().apply {
                arguments = Bundle().apply {
                    putString(key, "KEY")
                }
            }
    }
}
