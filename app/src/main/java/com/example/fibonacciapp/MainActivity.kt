package com.example.fibonacciapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fibonacciapp.adapter.FiboAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterView: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listFibonacciNumbers = getFibonacciNumbers(14)
        viewManager = LinearLayoutManager(this)
        adapterView = FiboAdapter(listFibonacciNumbers)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = adapterView
        }
    }

    private fun getFibonacciNumbers(limitNumber: Int): ArrayList<String>{
        var listOfFiboNumbers = ArrayList<String>()

        var i = 1
        var n1 = 1
        var n2 = 1

        listOfFiboNumbers.add("1")
        listOfFiboNumbers.add("1")

        while (i <= limitNumber) {
            val sum = n1 + n2
            listOfFiboNumbers.add(sum.toString())
            n1 = n2
            n2 = sum
            i++
        }
        return listOfFiboNumbers
    }
}
