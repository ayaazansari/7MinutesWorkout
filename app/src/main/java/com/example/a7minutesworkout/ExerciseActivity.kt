package com.example.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.lang.Exception
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    private var restTimer: CountDownTimer? = null

    private var restTimer30: CountDownTimer? = null

    private var binding: ActivityExerciseBinding? = null

    private var exerciseList: ArrayList<ExerciseModel> = Constants.defaultExerciseList()
    var i = 0

    private var player:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupActionBar()
        tts = TextToSpeech(this, this)


        binding?.leftArrow?.setOnClickListener{
            i--
            Log.e("Ayaaz","left clicked")
            exerciseScreen()
        }
        binding?.rightArrow?.setOnClickListener {
            i++
            Log.e("Ayaaz","Right clicked")
            exerciseScreen()
        }
        startTimer()
    }


    private fun startTimer() {
        showLoadingScreen()
        try{
            val soundURI = Uri.parse(
                "android.resource://com.example.a7minutesworkout/"+R.raw.press_start)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e:Exception){
            e.printStackTrace()
        }
        if (i >10) {
            startActivity(Intent(this,MainActivity::class.java))
        } else {
            val exercise = exerciseList[i].getName()
            binding?.upComingExercise?.text = "Upcoming Exercise\n${exercise}"
            Log.e("Ayaaz", "$i")
            restTimer = object : CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding?.progressBar?.progress =
                        Integer.parseInt((millisUntilFinished / 1000).toString())
                    binding?.tvTimer?.text = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    restTimer = null
                    exerciseScreen()
                }
            }.start()
        }
    }


    private fun exerciseScreen() {
        Log.e("Ayaaz2", "$i")
        val exercise = exerciseList[i]
        speakOut(exercise.getName())
        val exerciseName = exercise.getName()
        val exerciseImage = exercise.getImage()
        binding?.tvExercise?.text = exerciseName
        binding?.ivExercise?.setImageResource(exerciseImage)
        startTimer30()
    }

    private fun showExerciseScreen() {
        binding?.timer10?.visibility = View.GONE
        binding?.timer30?.visibility = View.VISIBLE
        binding?.upComingExercise?.visibility = View.GONE
        binding?.tvGetReady?.visibility = View.GONE
        binding?.ivExercise?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.leftArrow?.visibility = View.VISIBLE
        binding?.rightArrow?.visibility = View.VISIBLE
    }

    private fun showLoadingScreen() {
        binding?.timer10?.visibility = View.VISIBLE
        binding?.timer30?.visibility = View.GONE
        binding?.upComingExercise?.visibility = View.VISIBLE
        binding?.tvGetReady?.visibility = View.VISIBLE
        binding?.ivExercise?.visibility = View.GONE
        binding?.tvExercise?.visibility = View.GONE
        binding?.leftArrow?.visibility = View.GONE
        binding?.rightArrow?.visibility = View.GONE
    }

    private fun startTimer30() {
        showExerciseScreen()
        restTimer30 = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding?.progressBar30?.progress =
                    Integer.parseInt((millisUntilFinished / 1000).toString())
                binding?.tvTimer30?.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                i++
                startTimer()
                restTimer30 = null
            }
        }.start()
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


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("Ayaaz", "The language specified is not supported")
            }
        } else
            Log.e("Ayaaz", "Initialization failed")
    }

    private fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer!!.cancel()
            restTimer = null
        }
        if (restTimer30 != null) {
            restTimer30!!.cancel()
            restTimer30 = null
        }
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }
    }
}