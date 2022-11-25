package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.llbutton?.setOnClickListener {
            startActivity(Intent(this,ExerciseActivity::class.java))
        }

        binding?.btnBMI?.setOnClickListener{
            startActivity(Intent(this,BMIActivity::class.java))
        }

        llHistory.setOnClickListener {
            startActivity(Intent(this,ActivityHistory::class.java))
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}