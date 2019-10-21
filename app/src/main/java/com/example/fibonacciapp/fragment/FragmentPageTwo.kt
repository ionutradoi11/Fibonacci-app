package com.example.fibonacciapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room

import com.example.fibonacciapp.R
import kotlinx.android.synthetic.main.fragment_page_two.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fibonacciapp.JsonPlaceHolderApi
import com.example.fibonacciapp.MainActivity
import com.example.fibonacciapp.db.enitity.AppDatabase
import com.example.fibonacciapp.db.enitity.LOCATION_TABLE_ID
import com.example.fibonacciapp.db.enitity.Location
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

        val call = jsonPlaceHolderApi.locations

        call.enqueue(object : Callback<List<Location>>{

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                if (!response.isSuccessful){
                    titleView?.text = "Code: " + response.code()
                    return
                }

                val locations : List<Location>? = response.body()

                val firstElement = locations?.get(0)

                val firstImage = firstElement?.pictures?.get(0)

                titleView.text = firstElement?.title

                nameView.text = firstElement?.name

                Picasso.get()
                    .load(getUrl(id))
                    .into(imageView)
            }

            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                titleView?.text = t.message
            }

        })
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
        val id = (0..100).random()
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
