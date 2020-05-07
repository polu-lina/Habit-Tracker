package com.example.habittracker

import java.io.Serializable

data class Habit(var name: String = "",
                 var description: String = "",
                 var priority: String = "",
                 var type: String = "",
                 var color: String = "",
                 var quantity: String = "",
                 var frequency: String = ""): Serializable{

}