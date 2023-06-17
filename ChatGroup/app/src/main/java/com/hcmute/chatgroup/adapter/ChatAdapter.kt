package com.hcmute.chatgroup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hcmute.chatgroup.R
import com.hcmute.chatgroup.model.FriendMessage
import com.hcmute.chatgroup.model.MyMessage
import java.util.Objects

class ChatAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var chatList : List<Objects>
    private lateinit var context : Context
    companion object {
        const val MY_CHAT : Int = 1
        const val FRIEND_CHAT : Int = 0
    }
    constructor(chatList: List<Objects>, context: Context) : this() {
        this.chatList = chatList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var view : View
        return when (viewType) {
            1 ->  {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_message, parent, false)
                MyChatViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend_message, parent, false)
                FriendChatViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolderType : Int = getViewType(holder)
        when(viewHolderType) {
            1 -> {
                val itemChat = chatList.get(position) as MyMessage
                val viewHolder : MyChatViewHolder =  holder as (MyChatViewHolder)
                viewHolder.tvContent.text = itemChat.content
            }
            else -> {
                val itemChat = chatList.get(position) as FriendMessage
                val viewHolder : FriendChatViewHolder =  holder as (FriendChatViewHolder)
                viewHolder.tvContent.text = itemChat.content
            }
        }
    }
    private fun getViewType(view : RecyclerView.ViewHolder) : Int {
        if(view is MyChatViewHolder)
            return 1
        return 0
    }
    class MyChatViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val tvContent : TextView = itemView.findViewById(R.id.tv_content_friend_message_item)
    }
    class FriendChatViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val tvContent : TextView = itemView.findViewById(R.id.tv_content_my_message_item)
    }
}