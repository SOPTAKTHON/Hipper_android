package org.sopt.hipxercisesoptakthon.SignUp

data class ResponseSignUpData(
    val status: Int,
    val msg: String,
    val data: SignUpData?
) {
    data class SignUpData(
        val email: String,
        val nickname: String
    )
}
