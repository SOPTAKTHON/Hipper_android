package org.sopt.hipxercisesoptakthon.data

import com.google.gson.annotations.SerializedName

data class ResponseAlarmData (
    val statue : Int,
    val data : AlarmData?
)

data class AlarmData(
    val exercise : AlarmExerciseData?
)

data class AlarmExerciseData(
    val id : String,
    val sxercise_idx : Int,
    @SerializedName("exercise_name")
    val exerciseName : String,
    @SerializedName("exercise_cnt")
    val exerciseCnt : String
)
