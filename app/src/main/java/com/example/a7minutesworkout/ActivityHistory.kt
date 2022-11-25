package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.db.WorkoutApp
import com.example.a7minutesworkout.db.WorkoutDao
import com.example.a7minutesworkout.db.WorkoutEntity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.launch

class ActivityHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "History" // Setting an title in the action bar.

        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }

        val workoutDao = (application as WorkoutApp).db.workoutDao()

        lifecycleScope.launch {
            workoutDao.fetchAllData().collect {
                val list = ArrayList(it)
                setupListOfDataIntoRecyclerView(list, workoutDao)
            }
        }
    }
    private fun setupListOfDataIntoRecyclerView(
        list: ArrayList<WorkoutEntity>,
        workoutDao: WorkoutDao
    ) {
        if (list.isNotEmpty()) {
            val itemAdapter = ItemAdapter(
                this@ActivityHistory,
                list
            )
            rvList.layoutManager = LinearLayoutManager(this)
            rvList.adapter = itemAdapter
        }
    }
}