package com.example.fibonacciapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fibonacciapp.adapter.FiboAdapter
import com.example.fibonacciapp.fragment.FragmentPageTwo
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
            listNumbers = listFibonacciNumbers
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp(View(this))
    }

    fun setUp (view: View) {

      //  adapterView = FiboAdapter(listFibonacciNumbers, null)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        recyclerView.adapter = adapter

    }

    private fun getFibonacciNumbers(limitNumber: Int): ArrayList<String> {
        val listOfFiboNumbers = ArrayList<String>()

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
