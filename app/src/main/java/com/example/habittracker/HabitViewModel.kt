package com.example.habittracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitViewModel : ViewModel() {
    var habitList : MutableLiveData<ArrayList<Habit>> = MutableLiveData()

    init { habitList.value = ArrayList() }

    fun getListHabits() = habitList

    fun updateListHabits(habits: ArrayList<Habit>){
        habitList.value = habits
    }
}