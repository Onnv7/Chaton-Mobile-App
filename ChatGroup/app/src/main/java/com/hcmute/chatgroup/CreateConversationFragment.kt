package com.hcmute.chatgroup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.gson.JsonObject
import com.hcmute.chatgroup.api.RetrofitClient
import com.hcmute.chatgroup.api.RoomApiService
import com.hcmute.chatgroup.databinding.FragmentConversationBinding
import com.hcmute.chatgroup.databinding.FragmentCreateConversationBinding
import com.hcmute.chatgroup.utils.EditTextUtils
import com.hcmute.chatgroup.utils.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CreateConversationFragment : DialogFragment() {
    private lateinit var binding : FragmentCreateConversationBinding
    private lateinit var roomApiService: RoomApiService
    private var userId : String? = ""
    private fun setOnButtonClick(binding : FragmentCreateConversationBinding) {
        binding.btnCancelCreateRoomFrm.setOnClickListener{
            this.dismiss()
        }
        binding.btnCreateCreateRoomFrm.setOnClickListener{
            if(EditTextUtils.isEmpty(binding.edtRoomNameCreateRoomFrm)) {
                Toast.makeText(requireContext(), "Fill in your room name", Toast.LENGTH_SHORT).show()
                binding.edtRoomNameCreateRoomFrm.requestFocus()
                return@setOnClickListener
            }
            val body : JsonObject = JsonObject()
            body.addProperty("hostId", userId)
            body.addProperty("roomName", binding.edtRoomNameCreateRoomFrm.text.toString().trim())
            roomApiService.createRoom(body).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Toast.makeText(requireContext(), "Created", Toast.LENGTH_SHORT).show()
                    this@CreateConversationFragment.dismiss()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })
            Toast.makeText(requireContext(), "CREATE", Toast.LENGTH_SHORT).show()
            Log.d("NVA", "setOnButtonClick: ")
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        init()
        val inflater = LayoutInflater.from(requireContext())
        binding = FragmentCreateConversationBinding.inflate(inflater)
        val dialog  = AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setCancelable(false)
            .create()
        dialog.setCanceledOnTouchOutside(false)

        val width = resources.getDimensionPixelSize(R.dimen.popup_width)
        dialog.window?.setLayout(width, dialog.window?.attributes?.height ?: ViewGroup.LayoutParams.WRAP_CONTENT)

        setOnButtonClick(binding)
        return dialog
    }
    private fun init() {
        val userInfo = UserInfo(requireContext())
        roomApiService = RetrofitClient.getRetrofitClient().create(RoomApiService::class.java)
        userId = userInfo.getInfo("id")
    }
}