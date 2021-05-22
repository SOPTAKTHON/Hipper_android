package org.sopt.hipxercisesoptakthon.api

import org.sopt.hipxercisesoptakthon.data.ResponseAlarmData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AlarmService {
    @GET("/api/exercise")
    fun getAlarm() : Call<ResponseAlarmData>
}