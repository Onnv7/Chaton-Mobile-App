package com.hcmute.chatgroup.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hcmute.chatgroup.R
import com.hcmute.chatgroup.model.Conversation

class ConversationAdapter() : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {
    private lateinit var conversationList : List<Conversation>
    private lateinit var context : Context

    constructor(conversationList: List<Conversation>, context: Context) : this() {
        this.conversationList = conversationList
        this.context = context
    };


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return conversationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentConversation = conversationList[position]

        holder.groupName.text = currentConversation.groupName
        holder.latestMsg.text = currentConversation.latestMsg
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val groupName : TextView = itemView.findViewById(R.id.tv_group_name_conversation_item)
        val latestMsg : TextView = itemView.findViewById(R.id.tv_group_latest_msg_conversation_item)
    }
}