package org.sopt.hipxercisesoptakthon.SignIn

data class ResponseSignInData(
    val status: Int,
    val msg: String,
    val data: LoginData?
){
    data class LoginData(
        val email: String,
        val nickname: String,
        val token: String,
    )
}
