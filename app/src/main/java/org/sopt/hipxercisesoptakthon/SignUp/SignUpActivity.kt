package org.sopt.hipxercisesoptakthon.SignUp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.hipxercisesoptakthon.R
import org.sopt.hipxercisesoptakthon.api.ServiceCreator
import org.sopt.hipxercisesoptakthon.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonSignUpOnClickEvent()
    }

    private fun buttonSignUpOnClickEvent(){
        val nickname = binding.edittextSignUpNickname.text
        val email = binding.edittextSignUpEmail.text
        val pw = binding.edittextSignUpPw.text
        binding.buttonSignUpSignUp.setOnClickListener() {
            if (nickname.isNullOrBlank() || email.isNullOrBlank() || pw.isNullOrBlank()) {
                Toast.makeText(this, "빈 칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                checkSignUpInfo()
            }
        }
    }

    private fun checkSignUpInfo(){
        // 회원가입 데이터 생성
        val requestSignUpData = RequestSignUpData(
            email = binding.edittextSignUpEmail.text.toString(),
            password = binding.edittextSignUpPw.text.toString(),
            nickname = binding.edittextSignUpNickname.text.toString()
        )

        val call: Call<ResponseSignUpData> = ServiceCreator.signUpService.postSignup(requestSignUpData)

        call.enqueue(object: Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ){
                if(response.isSuccessful){
                    val data = response.body()?.data
                    Log.d("mylog", data?.nickname + ", " + response.body()?.msg)
                    Toast.makeText(this@SignUpActivity, data?.nickname +"님 회원가입을 축하합니다.", Toast.LENGTH_SHORT).show()
                    returnSignInActivity()
                } else {
                    // 에러처리 어떻게 할지 생각해보기
                    Toast.makeText(this@SignUpActivity, response.body()?.msg, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    private fun returnSignInActivity(){
        val intent = Intent()
        intent.putExtra("email", binding.edittextSignUpEmail.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}