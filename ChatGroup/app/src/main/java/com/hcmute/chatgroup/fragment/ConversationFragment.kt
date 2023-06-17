package com.hcmute.chatgroup.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hcmute.chatgroup.adapter.ConversationAdapter
import com.hcmute.chatgroup.databinding.FragmentConversationBinding
import com.hcmute.chatgroup.model.Conversation

class ConversationFragment : Fragment() {

    private var _binding: FragmentConversationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var conversationAdapter : ConversationAdapter;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConversationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        conversationAdapter = ConversationAdapter(getConversationData(), this.context!!)
        binding.rcvConversationFrmHome.adapter = conversationAdapter
        binding.rcvConversationFrmHome.layoutManager = LinearLayoutManager(this.context!!)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getConversationData() : MutableList<Conversation>{
        val list : MutableList<Conversation> = mutableListOf();
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        list.add(Conversation("", "Nguyen Van An", "Hello - 12:10"))
        return list
    }
}