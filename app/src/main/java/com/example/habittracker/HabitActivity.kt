package com.example.habittracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_habit.*
import kotlin.properties.Delegates

class HabitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit)
        getData()
    }

    var isEdit by Delegates.notNull<Boolean>()

    fun getData(){
        isEdit = intent.getBooleanExtra("isEdit", false)
        if (isEdit){
            edit_name.append(intent.getStringExtra("name"))
            edit_description.append(intent.getStringExtra("description"))
            edit_frequency.append(intent.getStringExtra("frequency"))
            edit_quantity.append(intent.getStringExtra("quantity"))
            val priority = intent.getStringExtra("priority")
            if (priority == "Высокий")
                spinner.setSelection(0)
            if (priority == "Средний")
                spinner.setSelection(1)
            if (priority == "Низкий")
                spinner.setSelection(2)
        }
    }

    fun saveHabit(view: View){
        val name = edit_name.text.toString()
        val description = edit_description.text.toString()
        val priority = spinner.selectedItem.toString()
        val type = SelectTypeHabit
        val frequency = edit_frequency.text.toString()
        val quantity = edit_quantity.text.toString()
        val isCorrectly = checkData(name, description, type, frequency, quantity)
        if (isCorrectly)
            sendData(name, description, priority, type!!, frequency, quantity)
        else
            Toast.makeText(applicationContext, "Заполните все поля", Toast.LENGTH_SHORT).show()
    }

    fun checkData(name: String?, description: String?, type: String?, frequency: String?, quantity: String?): Boolean{
        if(name != null && description != null && type != null)
            return true
        return false
    }

    fun sendData(name: String, description: String, priority: String, type: String, frequency: String, quantity: String){
        val addHabitIntent = Intent(this, MainActivity::class.java)
        addHabitIntent.putExtra("name", name)
        addHabitIntent.putExtra("description", description)
        addHabitIntent.putExtra("priority", priority)
        addHabitIntent.putExtra("type", type)
        addHabitIntent.putExtra("frequency", frequency)
        addHabitIntent.putExtra("quantity", quantity)
        if (isEdit)
            addHabitIntent.putExtra("position", intent.getIntExtra("position", 0))
        setResult(Activity.RESULT_OK, addHabitIntent)
        finish()
    }

    var SelectTypeHabit: String? = null

    fun selectTypeHabit(view: View){
        val id = view.id
        if(id == radio_good.id)
            SelectTypeHabit = radio_good.toString()
        if(id == radio_bad.id)
            SelectTypeHabit = radio_bad.toString()
    }
}