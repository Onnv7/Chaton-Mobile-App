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
        val chatList : List<Objects> = getDataChat()

        val chatAdapter : ChatAdapter = ChatAdapter(chatList, this)
        rcvChatList.adapter = chatAdapter
        val layout : LinearLayoutManager = LinearLayoutManager(this)
        rcvChatList.layoutManager = layout

    }

    private fun <T> getDataChat() : List<T> {
        val list : MutableList<T> = mutableListOf()

        list.add(MyMessage("Hello", "AT") as T)
        list.add(MyMessage("Hello", "AT") as T)
        list.add(FriendMessage("Hello", "AT") as T)
        list.add(MyMessage("Hello", "AT") as T)
        list.add(FriendMessage("Hello", "AT") as T)
        list.add(FriendMessage("Hello", "AT") as T)
        return list
    }
}