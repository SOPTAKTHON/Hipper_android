package org.sopt.hipxercisesoptakthon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginTop
import org.sopt.hipxercisesoptakthon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmButtonClickEvent()
        exerciseButtonClickEvent()


    }


    private fun alarmButtonClickEvent(){
        binding.imageviewButtonAlarmPush.setOnClickListener {
            it.isSelected = !it.isSelected
            //binding.imageviewButtonAlarm.marginTop(140F)

            //커스텀 토스트
            var v1 = layoutInflater.inflate(R.layout.custom_toast, null)
            var t2 = Toast(this)
            t2.view = v1
            t2.show()

            //다이얼로그 띄우기
        }



    }

    private fun exerciseButtonClickEvent(){
        binding.imageviewButtonExerciseUnpush.setOnClickListener {
            it.isSelected = !it.isSelected
            val builder = AlertDialog.Builder(this)

            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

            builder.setView(dialogView)
                    .show()

        }



    }



}