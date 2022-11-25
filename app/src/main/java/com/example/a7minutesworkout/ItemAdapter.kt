package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.db.WorkoutEntity
import kotlinx.android.synthetic.main.activity_finish.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class ItemAdapter(
    private val context:Context,
    private val list:ArrayList<WorkoutEntity>
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if(holder is MyViewHolder){
            holder.itemView.tvNum.text = model.id.toString()
            holder.itemView.tvDate.text = model.date
            holder.itemView.tvTime.text = model.time
        }

        if(position%2==0){
            holder.itemView.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorLightGray
                )
            )
        }
        else{
            holder.itemView.llMain.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

}