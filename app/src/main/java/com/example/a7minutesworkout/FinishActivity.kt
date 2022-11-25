package com.example.a7minutesworkout

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.app.AppCompatActivity
import com.example.a7minutesworkout.databinding.ActivityFinishBinding
import com.example.a7minutesworkout.db.WorkoutApp
import com.example.a7minutesworkout.db.WorkoutDao
import com.example.a7minutesworkout.db.WorkoutEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private var binding:ActivityFinishBinding?= null
    private var currentDate:String? = null
    private var currentTime:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupActionBar()
        getDateAndTime()
        val workoutDao = (application as WorkoutApp).db.workoutDao()

        binding?.btnFinish?.setOnClickListener {
            addRecord(workoutDao)
            finish()
        }
    }

    private fun addRecord(workoutDao: WorkoutDao){
        lifecycleScope.launch {
            workoutDao.insert(WorkoutEntity(date=currentDate, time = currentTime))
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back)
        }
        binding?.toolbar?.setNavigationOnClickListener { onBackPressed() }
    }

    private fun getDateAndTime(){
        currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    }
}