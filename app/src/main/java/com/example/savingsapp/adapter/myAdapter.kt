package com.example.savingsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savingsapp.R
import com.example.savingsapp.model.Post
import kotlinx.android.synthetic.main.row_layout.view.*
import kotlin.math.roundToInt

class myAdapter: RecyclerView.Adapter<myAdapter.MyViewHolder>(){
    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
        }


        override fun getItemCount(): Int {
            return myList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            holder.itemView.tv_userId.text= myList[position].userId.toString()
            holder.itemView.tv_Id.text= myList[position].id.toString()
            holder.itemView.tv_title.text= myList[position].title.toString()
            holder.itemView.tv_body.text= myList[position].body.toString()
        }


        fun setData(newList: List<Post>){
            myList= newList
            notifyDataSetChanged()
        }

    }
