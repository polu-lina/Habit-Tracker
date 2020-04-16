package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.habit_item.view.*

class RecyclerAdapter: RecyclerView.Adapter <RecyclerAdapter.ViewHolder> () {

    private var habits: ArrayList<Habit> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.habit_item, parent, false)
        return ViewHolder(view).listen { pos, type ->
            val habit = habits[pos]
            if(parent.context is MainActivity){
                (parent.context as MainActivity).editHabit(habit, pos)
            }
        }
    }

    override fun getItemCount() =habits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.habitName.text = habits[position].name
        holder.habitDescription.text = habits[position].description
        holder.adapterPosition
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val habitName: TextView = view.name
        val habitDescription: TextView = view.description
        fun listen(event: (position: Int, type: Int) -> Unit): ViewHolder {
            itemView.setOnClickListener {
                event.invoke(adapterPosition, itemViewType)
            }
            return this
        }
    }

    fun refreshHabits(habits: ArrayList<Habit>){
        this.habits = habits
        notifyDataSetChanged()
    }
}