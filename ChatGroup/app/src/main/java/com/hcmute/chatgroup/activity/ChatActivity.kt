package com.hcmute.chatgroup.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hcmute.chatgroup.R
import com.hcmute.chatgroup.adapter.ChatAdapter
import com.hcmute.chatgroup.databinding.ActivityChatBinding
import com.hcmute.chatgroup.model.Conversation
import com.hcmute.chatgroup.model.FriendMessage
import com.hcmute.chatgroup.model.MyMessage
import java.util.Objects

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rcvChatList : RecyclerView = binding.rcvMessageChat
        val chatList : List<Any> = getDataChat()


        val chatAdapter : ChatAdapter = ChatAdapter(chatList, this)
        rcvChatList.adapter = chatAdapter

        val layout : LinearLayoutManager = LinearLayoutManager(this)
        rcvChatList.layoutManager = layout

    }

    private fun getDataChat() : List<Any> {
        val itemList = listOf(
            MyMessage("Hell452452o", "AT"),
            MyMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            FriendMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            FriendMessage("sadasd", "AT"),
            MyMessage("Hello", "AT"),

            MyMessage("Hell452452o", "AT"),
            MyMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            FriendMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            FriendMessage("sadasd", "AT"),
            FriendMessage("sadasd", "AT"),
            MyMessage("Hello", "AT"),

            MyMessage("Hell452452o", "AT"),
            MyMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            FriendMessage("Helldsffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffo", "AT"),
            MyMessage("Hello", "AT"),

            )
        return itemList
    }
}