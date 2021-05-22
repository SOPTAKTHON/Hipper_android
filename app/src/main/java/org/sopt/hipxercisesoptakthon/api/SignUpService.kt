package org.sopt.hipxercisesoptakthon.api

import org.sopt.hipxercisesoptakthon.SignUp.RequestSignUpData
import org.sopt.hipxercisesoptakthon.SignUp.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/api/users")
    fun postSignup(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>
}