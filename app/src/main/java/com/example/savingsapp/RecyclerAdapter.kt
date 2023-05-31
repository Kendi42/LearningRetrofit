package com.example.savingsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import kotlin.math.roundToInt

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var savingGoals= arrayOf("Buy a House", "Buy a car", "Holiday")
    private var amountSaved= arrayOf("2000000", "4000", "8000")
    private var toBeSaved= arrayOf("1000000", "26000", "2000")





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.savings_card, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return savingGoals.size

    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var savingTV: TextView
        var tv_saved:TextView
        var tv_toBeSaved: TextView
        var progressBar: ProgressBar
        var tv_percentage:TextView

        init{
            savingTV = itemView.findViewById(R.id.tv_savingGoal)
            tv_saved= itemView.findViewById(R.id.tv_saved)
            tv_toBeSaved= itemView.findViewById(R.id.tv_toBeSaved)
            progressBar= itemView.findViewById(R.id.progressBar)
            tv_percentage= itemView.findViewById(R.id.tv_percentage)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.savingTV.text= savingGoals[position]
        holder.tv_saved.text= amountSaved[position]
        holder.tv_toBeSaved.text= toBeSaved[position]

        var valueSaved = amountSaved[position].toInt()
        var valueToSave= toBeSaved[position].toInt()
        var totalValue= valueSaved + valueToSave
        val progressValue = ((valueSaved.toDouble() / totalValue.toDouble()) * 100).roundToInt()
        holder.progressBar.progress=progressValue
        holder.tv_percentage.text= progressValue.toString()+ "%"


    }

}