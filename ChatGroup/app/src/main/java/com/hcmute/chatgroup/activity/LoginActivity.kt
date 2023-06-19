package com.hcmute.chatgroup.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.JsonObject
import com.hcmute.chatgroup.R
import com.hcmute.chatgroup.api.AuthApiService
import com.hcmute.chatgroup.api.RetrofitClient
import com.hcmute.chatgroup.api.UserApiService
import com.hcmute.chatgroup.databinding.ActivityLoginBinding
import com.hcmute.chatgroup.utils.Constants
import com.hcmute.chatgroup.utils.EditTextUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var authApiService: AuthApiService
    private lateinit var sharedPreferences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        checkLoggedIn()
        EditTextUtils.setOnVisiblePasswordSwitch(binding.edtPasswordLoginAct)
        setOnButtonLoginClick()
        setOnClickRegister()
    }
    private fun init() {
        sharedPreferences = getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE)
        authApiService = RetrofitClient.getRetrofitClient().create(AuthApiService::class.java)
    }
    private fun checkLoggedIn() {
        if(sharedPreferences.getBoolean("is_logged_in", false)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun setOnButtonLoginClick() {
        binding.btnLoginLoginAct.setOnClickListener{
            if(checkLoginInfo()) {
                val body = JsonObject()
                body.addProperty("username", binding.edtEmailLoginAct.text.toString())
                body.addProperty("password", binding.edtPasswordLoginAct.text.toString())
                authApiService.login(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if(response.isSuccessful) {
                            val res = response.body()
                            if(res?.get("success")!!.asBoolean) {
                                val result = res.get("result").asJsonObject
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                                val editor = sharedPreferences.edit()
                                editor.putString("id", result.get("id").asString)
                                editor.putString("name", result.get("name").asString)
                                editor.putString("email", result.get("email").asString)
                                editor.putBoolean("is_logged_in", true)
                                editor.apply()
                            }else {
                                Toast.makeText(this@LoginActivity, "Email or password is wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else
                print("not")
        }
    }
    private fun checkLoginInfo() : Boolean {
        var flag = true
        if(EditTextUtils.isEmpty(binding.edtEmailLoginAct)) {
            binding.edtEmailLoginAct.error = "Please, fill in your email information"
            binding.edtEmailLoginAct.requestFocus()
            flag = false
        }
        if(EditTextUtils.isEmpty(binding.edtPasswordLoginAct)) {
            binding.edtPasswordLoginAct.error = "Please, fill in your password"
            binding.edtPasswordLoginAct.requestFocus()
            flag = false
        }
        return flag

    }
    private fun setOnClickRegister() {
        binding.tvRegisterLoginAct.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}