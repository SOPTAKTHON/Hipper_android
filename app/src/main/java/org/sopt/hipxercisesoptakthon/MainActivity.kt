package org.sopt.hipxercisesoptakthon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginTop
import org.sopt.hipxercisesoptakthon.api.ServiceCreator
import org.sopt.hipxercisesoptakthon.data.ResponseAlarmData
import org.sopt.hipxercisesoptakthon.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmButtonClickEvent()
        exerciseButtonClickEvent()


    }


    private fun alarmButtonClickEvent() {
        //binding.imageviewButtonAlarm.setOnClickListener {
            //it.isSelected = !it.isSelected
            //binding.imageviewButtonAlarm.marginTop(140F)

            binding.imageviewButtonAlarmPush.setOnClickListener {
                binding.imageviewButtonAlarmPush.visibility = View.GONE
                binding.imageviewButtonAlarmUnpush.visibility = View.VISIBLE
                //커스텀 토스트
             //   if (it.isSelected) {
                    alarmDialog()

                    it.marginTop
                    var v1 = layoutInflater.inflate(R.layout.custom_toast, null)
                    var t2 = Toast(this)
                    t2.view = v1
                    t2.show()
              //  }
            }

            binding.imageviewButtonAlarmUnpush.setOnClickListener {
                binding.imageviewButtonAlarmPush.visibility = View.VISIBLE
                binding.imageviewButtonAlarmUnpush.visibility = View.GONE
            }

      //  }


    }

    private fun exerciseButtonClickEvent() {
        binding.imageviewButtonExercisePush.setOnClickListener {
            binding.imageviewButtonExercisePush.visibility = View.GONE
            binding.imageviewButtonExerciseUnpush.visibility = View.VISIBLE
        }

        binding.imageviewButtonExerciseUnpush.setOnClickListener {
            binding.imageviewButtonExercisePush.visibility = View.VISIBLE
            binding.imageviewButtonExerciseUnpush.visibility = View.GONE
        }


    }

    private fun alarmDialog() {
        val builder = AlertDialog.Builder(this)

        val dialogView = layoutInflater.inflate(R.layout.alarm_dialog, null)

        val dialogText1 = dialogView.findViewById<TextView>(R.id.textview_rightnow)
        val dialogText2 = dialogView.findViewById<TextView>(R.id.textview_what_count)
        val dialogText3 = dialogView.findViewById<TextView>(R.id.textview_start)

        builder.setView(dialogView)
            .show()

            val call: Call<ResponseAlarmData> = ServiceCreator.alarmService.getAlarm()

            call.enqueue(object : Callback<ResponseAlarmData> {
                override fun onResponse(
                    call: Call<ResponseAlarmData>,
                    response: Response<ResponseAlarmData>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        dialogText2.text = "${data?.exercise?.exerciseName} ${data?.exercise?.exerciseCnt}"

                        Log.d("test","통신 성공")
                    } else {
                        //에러났을 때 코드
                        Log.d("test","통신 실패")
                    }
                }

                override fun onFailure(call: Call<ResponseAlarmData>, t: Throwable) {
                    Log.d("NetworkTestSignIn", "error:$t")
                }
            })

    }
}
