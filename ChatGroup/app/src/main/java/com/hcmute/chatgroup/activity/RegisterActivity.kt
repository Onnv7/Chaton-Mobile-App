package com.hcmute.chatgroup.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.JsonObject
import com.hcmute.chatgroup.R
import com.hcmute.chatgroup.api.RetrofitClient
import com.hcmute.chatgroup.api.UserApiService
import com.hcmute.chatgroup.databinding.ActivityRegisterBinding
import com.hcmute.chatgroup.utils.EditTextUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var userApiService: UserApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        userApiService = RetrofitClient.getRetrofitClient().create(UserApiService::class.java)
        setContentView(binding.root)
        EditTextUtils.setOnVisiblePasswordSwitch(binding.edtPasswordRegisterAct)
        EditTextUtils.setOnVisiblePasswordSwitch(binding.edtRePasswordRegisterAct)
        setOnButtonRegisterClick()
        setOnLoginClick()
    }
    private fun setOnButtonRegisterClick() {
        binding.btnSignUpRegisterAct.setOnClickListener{
            if(checkRegisterInfo()) {
                val body = JsonObject()
                body.addProperty("name", binding.edtNameRegisterAct.text.toString())
                body.addProperty("email", binding.edtEmailRegisterAct.text.toString())
                body.addProperty("password", binding.edtRePasswordRegisterAct.text.toString())

                if(binding.rdogGenderRegisterAct.checkedRadioButtonId != -1) {
                    val rdoChecked = findViewById<RadioButton>(binding.rdogGenderRegisterAct.checkedRadioButtonId)
                    body.addProperty("gender", rdoChecked.text.toString().uppercase())
                }
                userApiService.registerAccount(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if(response.isSuccessful) {
                            val res = response.body()
                            println("OKOKOKOK")
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }
        }
    }
    private fun checkRegisterInfo() : Boolean {
        var flag = true
        if(EditTextUtils.isEmpty(binding.edtNameRegisterAct)) {
            binding.edtNameRegisterAct.error = "Please enter your name"
            binding.edtNameRegisterAct.requestFocus()
            flag = false
        }

        if(EditTextUtils.isEmpty(binding.edtEmailRegisterAct)) {
            binding.edtEmailRegisterAct.error = "Please enter your email"
            binding.edtEmailRegisterAct.requestFocus()
            flag = false
        }

        if(EditTextUtils.isEmpty(binding.edtPasswordRegisterAct)) {
            binding.edtPasswordRegisterAct.error = "Please enter your password"
            binding.edtPasswordRegisterAct.requestFocus()
            flag = false
        }

        if(EditTextUtils.isEmpty(binding.edtRePasswordRegisterAct)) {
            binding.edtRePasswordRegisterAct.error = "Please enter your re-password"
            binding.edtRePasswordRegisterAct.requestFocus()
            flag = false
        }
        if(binding.edtPasswordRegisterAct.text.toString() != binding.edtRePasswordRegisterAct.text.toString()) {
            println(binding.edtPasswordRegisterAct.text.toString() + "\n" + binding.edtRePasswordRegisterAct.text)
            binding.edtRePasswordRegisterAct.error = "Re-password is wrong"
            binding.edtRePasswordRegisterAct.setText("")
            binding.edtRePasswordRegisterAct.requestFocus()
            flag = false
        }
        if(binding.rdogGenderRegisterAct.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please choose your gender", Toast.LENGTH_SHORT).show()
            flag = false
        }
        if(!binding.cbAgreeRegisterAct.isChecked) {
            binding.cbAgreeRegisterAct.error = "You must agree to our policy"
            flag = false
        }
        return flag
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnVisiblePasswordSwitch(editText : EditText) {

        editText.setOnTouchListener{ view, event ->
            val drawable = editText.compoundDrawables[2]

            if(event.action == MotionEvent.ACTION_UP) {
                val drawableRight : Rect = drawable?.bounds ?: return@setOnTouchListener false
                val location = IntArray(2)
                editText.getLocationOnScreen(location)
                val emptySpace = (editText.height - editText.paddingTop
                        - editText.paddingBottom - drawable.bounds.height())/2
                val y = event.rawY.toInt() - location[1] - emptySpace;
                val x = event.rawX.toInt() - (editText.right - editText.compoundPaddingRight )


                if ((x >= drawableRight.left && x <= drawableRight.right) && (y >= 0 && y <= drawable.bounds.height())) {
                    if (editText.inputType and InputType.TYPE_CLASS_TEXT == InputType.TYPE_CLASS_TEXT &&
                        editText.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD == InputType.TYPE_TEXT_VARIATION_PASSWORD) {

                        editText.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        val visibleDrawable: Drawable? =
                            ContextCompat.getDrawable(this, R.drawable.icon_visibility)
                        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, visibleDrawable, null)
                    } else {
                        editText.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        val visibleOffDrawable: Drawable? =
                            ContextCompat.getDrawable(this, R.drawable.icon_visibility_off)
                        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, visibleOffDrawable, null)
                    }


                    return@setOnTouchListener true
                }
            }

            false
        }

    }
    private fun setOnLoginClick() {
        binding.tvLoginRegisterAct.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}