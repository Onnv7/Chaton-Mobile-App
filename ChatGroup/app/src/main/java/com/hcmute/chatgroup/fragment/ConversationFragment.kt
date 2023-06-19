package com.hcmute.chatgroup.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hcmute.chatgroup.CreateConversationFragment
import com.hcmute.chatgroup.R
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

        (activity as AppCompatActivity).supportActionBar?.title = "Your room"
//        val toolBar = requireActivity().findViewById<Toolbar>(R.id.toolbar_main)
//        toolBar.icon
//        (activity as AppCompatActivity).supportActionBar?.setIcon(R.drawable.icon_create_new_conversation)
//        toolBar.setNavigationIcon(R.drawable.icon_create_new_conversation) // Thiết lập biểu tượng điều hướng
//        toolBar.setLogo(R.drawable.icon_create_new_conversation)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        if(idItem == R.id.action_create_new_conversation) {
            val showPopup = CreateConversationFragment()
            showPopup.show((activity as AppCompatActivity).supportFragmentManager, "showPopup")
        }
        return super.onOptionsItemSelected(item)
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