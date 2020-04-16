package com.example.habittracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val habitViewModel by lazy {ViewModelProviders.of(this)
        .get(HabitViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecyclerAdapter()
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        habitViewModel.getListHabits().observe(this, Observer {
            it?.let{
                adapter.refreshHabits(it)
            }
        })
    }

    fun addHabit(view: View){
        val habitIntent = Intent(this, HabitActivity::class.java)
        habitIntent.putExtra("isEdit", false)
        startActivityForResult(habitIntent, 1)
    }

    fun editHabit(habit: Habit, position: Int){
        val habitIntent = Intent(this, HabitActivity::class.java)
        habitIntent.putExtra("isEdit", true)
        habitIntent.putExtra("name", habit.name)
        habitIntent.putExtra("description", habit.description)
        habitIntent.putExtra("type", habit.type)
        habitIntent.putExtra("frequency", habit.frequency)
        habitIntent.putExtra("priority", habit.priority)
        habitIntent.putExtra("quantity", habit.quantity)
        habitIntent.putExtra("position", position)
        startActivityForResult(habitIntent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val habit = Habit()
            habit.name = data?.getStringExtra("name").toString()
            habit.description = data?.getStringExtra("description").toString()
            habit.type = data?.getStringExtra("type").toString()
            habit.frequency = data?.getStringExtra("frequency").toString()
            habit.color = data?.getStringExtra("color").toString()
            habit.priority = data?.getStringExtra("priority").toString()
            val habits = habitViewModel.getListHabits().value
            if (requestCode == 1){
                habits?.add(habit)
            }
            if (requestCode == 2){
                val pos = data?.getIntExtra("position", 0)
                habits!![pos!!] = habit
            }
            habitViewModel.updateListHabits(habits!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}