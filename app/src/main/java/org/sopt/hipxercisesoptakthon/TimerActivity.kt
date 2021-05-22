package org.sopt.hipxercisesoptakthon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.hipxercisesoptakthon.databinding.ActivityTimerBinding
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class TimerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimerBinding
    private var time = 0
    private var isRunning = false
    private var timerTask : Timer?= null
    private var index : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        stopTimer()
    }

    fun startTimer(){
        timerTask = kotlin.concurrent.timer(period = 10){
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                binding.textviewClockMinute.text = "$sec"
                binding.textviewClockSecond.text = "$milli"
            }
        }

    }
    fun stopTimer(){
        binding.circleimageviewStop.setOnClickListener {
            timerTask?.cancel()
        }
    }



}