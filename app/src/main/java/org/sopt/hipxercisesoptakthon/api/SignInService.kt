package org.sopt.hipxercisesoptakthon.api

import org.sopt.hipxercisesoptakthon.SignIn.RequestSignInData
import org.sopt.hipxercisesoptakthon.SignIn.ResponseSignInData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/api/auth")
    fun postSignin(
        @Body body: RequestSignInData
    ): Call<ResponseSignInData>
}