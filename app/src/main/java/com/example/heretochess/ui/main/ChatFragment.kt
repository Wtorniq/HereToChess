package com.example.heretochess.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.heretochess.MainActivity
import com.example.heretochess.R
import com.example.heretochess.databinding.FragmentChatBinding
import com.example.heretochess.databinding.FragmentMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.SyncTree.CompletionListener
import com.google.firebase.database.core.view.Event
import java.util.*

private const val ARG_USER_ID = "userId"
private const val ARG_OTHER_USER_ID = "otherUserId"
private const val ARG_CHAT_ID = "chatId"

class ChatFragment : Fragment() {

    private var userId: String? = null
    private var otherUserId: String? = null
    private var chatId: String? = null

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_USER_ID)
            otherUserId = it.getString(ARG_OTHER_USER_ID)
            chatId = it.getString(ARG_CHAT_ID) ?: UUID.randomUUID().toString()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseDatabase.getInstance().getReference("chats/$chatId/users/$userId").setValue(true)
        FirebaseDatabase.getInstance().getReference("chats/$chatId/users/$otherUserId").setValue(true)
        with(binding){
            submitBtn.setOnClickListener {
                val message = textInput.text.toString()
                FirebaseDatabase.getInstance().getReference("chats/$chatId/message").push()
                    .setValue(message)
            }
            submitBtn.setOnLongClickListener {
                FirebaseDatabase.getInstance().getReference("chats/$chatId/message").removeValue()
                ll.invalidate()
                return@setOnLongClickListener true
            }

            FirebaseDatabase.getInstance().getReference("chats/$chatId/message")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val lastMessage = snapshot.children.lastOrNull()?.value
                        val tv = TextView(requireContext())
                        tv.text = lastMessage as String? ?: ""
                        ll.addView(tv)
                    }


                    override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "onCanceled!!!!!!", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: String, otherUserId : String, chatId : String?) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_ID, userId)
                    putString(ARG_OTHER_USER_ID, otherUserId)
                    putString(ARG_CHAT_ID, chatId)
                }
            }
    }
}