package com.example.fibonacciapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.fibonacciapp.R
import kotlinx.android.synthetic.main.fragment_page_two.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.fibonacciapp.JsonPlaceHolderApi
import com.example.fibonacciapp.Location
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class FragmentPageTwo : Fragment() {

    private var param1: String? = null
    var textViewResults: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(key)
        }

        textViewResults = titleView

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-0c3260-ropa.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call = jsonPlaceHolderApi.locations

        call.enqueue(object : Callback<List<Location>>{
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                textViewResults?.text = t.message
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                if (!response.isSuccessful){
                    textViewResults?.text = "Code: " + response.code()
                    return
                }

                val locations : List<Location>? = response.body()

                for (location in locations!!) {
                    var content = ""
                    content += "id: " + location.id + "\n"
                    content += "title: " + location.title + "\n"
                    content += "name: " + location.name + "\n"
                    content += "image: " + location.image + "\n\n"

                    textViewResults?.append(content)
                }

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
