package org.sopt.hipxercisesoptakthon.SignIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.hipxercisesoptakthon.MainActivity
import org.sopt.hipxercisesoptakthon.R
import org.sopt.hipxercisesoptakthon.SignUp.SignUpActivity
import org.sopt.hipxercisesoptakthon.api.ServiceCreator
import org.sopt.hipxercisesoptakthon.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private var _email = ""
    private var _nickname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonSignInOnClickEvent()
        textviewSignUpOnClickEvent()
    }

    // 로그인 런처
    private val mainActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){

    }

    // 회원가입 런처
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        binding.edittextSignInEmail.setText(it.data?.getStringExtra("email"))
        _email = it.data?.getStringExtra("email").toString()
        _nickname = it.data?.getStringExtra("nickname").toString()
    }

    // 로그인 클릭
    private fun buttonSignInOnClickEvent(){
        binding.buttonSignInSignIn.setOnClickListener(){
            val email = binding.edittextSignInEmail.text
            val pw = binding.edittextSignInPw.text
            if (email.isNullOrBlank() || pw.isNullOrBlank()) {
                Toast.makeText(this@SignInActivity, "아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                checkLoginInfo()
            }
        }
    }

    private fun checkLoginInfo(){
        val requestSignInData = RequestSignInData(
            email = binding.edittextSignInEmail.text.toString(),
            password = binding.edittextSignInPw.text.toString()
        )

        // 현재 사용자의 정보를 받아올 것을 명시!
        // 서버 통신은 I/O 작업이므로 비동기적으로 받아올 Callback 내부 코드는 나중에
        // 데이터를 받아오고 실행된다.
        val call: Call<ResponseSignInData> = ServiceCreator.signInService.postSignin(requestSignInData)

        // enqueue 함수를 이용해 Call이 비동기 작업이후 동작할 Callback 을 등록할 수 있다.
        // 해당 함수 호출은 Callback을 등록만 하고
        // 실제 서버 통신을 요청이후 통신 결과가 나왔을 때 실행된다.
        // object 키워드로 Callback을 구현할 익명 클래스를 생성
        call.enqueue(object: Callback<ResponseSignInData> {
            // 네트워크 통신 Response 가 있는 경우 해당 함수를 retrofit이 호출
            override fun onResponse(
                call: Call<ResponseSignInData>,
                response: Response<ResponseSignInData>
            ) {
                // 네트워크 통신에 성공한 경우 status 코드가 200~300일 때! 실행
                if(response.isSuccessful){
                    // response body 자체가 nullable 데이터! 그런데 서버에서 오는 data 도 nullable!
                    val data = response.body()?.data
                    Log.d("logintest", "success")
                    // 통신 성공시 유저 닉네임을 보여준다.
                    Toast.makeText(this@SignInActivity, data?.nickname + "님이 로그인하였습니다.", Toast.LENGTH_SHORT).show()
                    // 홈 화면으로 넘어간다.
                    _email = binding.edittextSignInEmail.text.toString()
                    _nickname = data?.nickname.toString()
                    startMainActivity()
                } else{
                    // 에러가 났다면?
                    Log.d("logintest", "fail")
                }
            }

            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("email", _email)
        intent.putExtra("nickname", _nickname)
        mainActivityLauncher.launch(intent)
    }

    // 회원가입 클릭
    private fun textviewSignUpOnClickEvent(){
        binding.textviewSignInSignUp.setOnClickListener(){
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }
}