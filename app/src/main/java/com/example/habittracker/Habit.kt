package com.example.habittracker

class Habit(){
    var name = ""
    var description = ""
    var priority = ""
    var type = ""
    var color = ""
    var quantity = ""
    var frequency = ""
    constructor(name: String, description: String, priority: String, type: String, quantity: String, frequency: String) : this() {
        this.name = name
        this.description = description
        this.priority = priority
        this.type = type
        this.quantity = quantity
        this.frequency = frequency
    }
}