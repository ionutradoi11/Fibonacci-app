package com.example.fibonacciapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fibonacciapp.Model.FiboModel
import com.example.fibonacciapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fibo_item.view.*

class FiboAdapter(
    private val list: ArrayList<FiboModel>,
    private val isButtonClickedlistener: (() -> Unit)?
) : RecyclerView.Adapter<FiboAdapter.FiboViewHolder>() {

    private val listNumbers: ArrayList<FiboModel> = ArrayList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiboViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fibo_item, parent, false)
        return FiboViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiboViewHolder, position: Int) {
        holder.button.setOnClickListener{
            if (holder.checkBox.isChecked) {
                isButtonClickedlistener?.invoke()
            }
        }
        val item = listNumbers[position]
        holder.numberView.text = item.number
        holder.checkBox.setOnCheckedChangeListener { compoundButton, b ->
            item.isChecked = b
        }
        holder.checkBox.isChecked = item.isChecked

        if (isNonPrimeNumber(item.number?.toInt() ?: 0)) {
            holder.rightSide.visibility = View.INVISIBLE
            Picasso.get()
                .load(getUrl(item.number?.toInt() ?: 0))
                .resize(100, 100)
                .centerCrop()
                .into(holder.imageView)
        } else {
            holder.rightSide.visibility = View.VISIBLE
            holder.imageView.setImageResource(0) //clear imageView
        }
    }

    class FiboViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val numberView: TextView = view.numberView
        val imageView: ImageView = view.imageView
        val rightSide: View = view.rightSide
        val button: View = view.btnCheck
        val checkBox: CheckBox = view.checkBox
    }


    override fun getItemCount() = listNumbers.size

    private fun getUrl(id: Int): String {
        return "https://picsum.photos/id/$id/200"
    }

    private fun isNonPrimeNumber(number: Int): Boolean {
        if (number == 1) return true
        if (number == 2) return false
        if (number == 3) return false
        if (number == 5) return false

        var flag = false
        for (i in 2..number / 2) {

            // condition for nonprime number
            if (number % i == 0) {
                flag = true
                break
            }
        }
        return flag
    }

}