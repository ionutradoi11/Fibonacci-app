package com.example.fibonacciapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.fibonacciapp.Model.FiboModel
import com.example.fibonacciapp.adapter.FiboAdapter
import com.example.fibonacciapp.db.enitity.AppDatabase
import com.example.fibonacciapp.db.enitity.App_Entity
import com.example.fibonacciapp.db.enitity.Location
import com.example.fibonacciapp.fragment.FragmentPageTwo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   // private lateinit var adapterView: RecyclerView.Adapter<*>
   private val listFibonacciNumbers = getFibonacciNumbers(14)

    private val adapter: FiboAdapter by lazy {
        FiboAdapter(
            isButtonClickedlistener = {
                val newFragment = FragmentPageTwo.newInstance()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.layout, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            },
            list = listFibonacciNumbers
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp(View(this))

        /*var db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "AppDB").build()

        var loc = App_Entity()
        loc.location_id = 1
        loc.location_name = "Dimitrie Cantemir"
        loc.location_title = "Biblioteca Sainte-Genevieve"
        loc.location_picture = Picasso.get()
            .load(getUrl(id = 46)).toString()

        db.appDAO().saveLocation(loc)

        db.appDAO().getLocation().forEach {
            Log.i("@BLABLA", """"Id is : ${it.location_id}"""")
            Log.i("@BLABLA", """"Name is : ${it.location_title}"""")
            Log.i("@BLABLA", """"Title is : ${it.location_name}"""")
            Log.i("@BLABLA", """"Picture is : ${it.location_picture}"""")
        } */
    }

    fun setUp (view: View) {

      //  adapterView = FiboAdapter(listFibonacciNumbers, null)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        recyclerView.adapter = adapter

    }

    private fun getUrl(id: Int): String {
        val id = (0..100).random()
        return "https://picsum.photos/id/$id/200"
    }

    private fun getFibonacciNumbers(limitNumber: Int): ArrayList<FiboModel> {
        val listOfFiboNumbers = ArrayList<FiboModel>()

        var i = 1
        var n1 = 1
        var n2 = 1

        listOfFiboNumbers.add(FiboModel("1"))
        listOfFiboNumbers.add(FiboModel("1"))

        while (i <= limitNumber) {
            val sum = n1 + n2
            listOfFiboNumbers.add(FiboModel(sum.toString()))
            n1 = n2
            n2 = sum
            i++
        }
        return listOfFiboNumbers
    }
}
