package com.example.heretochess.ui.main

import android.os.Bundle
import android.renderscript.Sampler.Value
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.heretochess.R
import com.example.heretochess.databinding.FragmentMainBinding
import com.example.heretochess.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firebase.ktx.initialize

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private var firstPlayerId = ""
    private var secondPlayerId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            authBtn.setOnClickListener {
                FirebaseAuth.getInstance().signInAnonymously().addOnSuccessListener {
                    Toast.makeText(requireContext(), "id: ${it.user?.uid}", Toast.LENGTH_SHORT).show()
                }
            }
            createBtn.setOnClickListener {
                val playerName = FirebaseAuth.getInstance().currentUser?.uid
                createNewLobby(playerName)
            }
            joinBtn.setOnClickListener {
                val playerName = FirebaseAuth.getInstance().currentUser?.uid
                joinLobby("lobby", playerName!!)
            }
            startBtn.setOnClickListener {
                val playerName = FirebaseAuth.getInstance().currentUser?.uid
                setPlayerReady("lobby", playerName!!)
            }
        }
    }

    private fun createNewLobby(playerName: String?) {
        val lobbyId = /*database.child("lobbies").push().key*/ "lobby"
        val lobby = hashMapOf<String, Any?>(
            "player1" to playerName,
            "player2" to null,
            "player1Ready" to false,
            "player2Ready" to false
        )
        database.child("lobbies").child(lobbyId).setValue(lobby)
    }

    private fun joinLobby(lobbyId: String, playerName: String) {
        database.child("lobbies").child(lobbyId).child("player2").setValue(playerName)
    }

    private fun setPlayerReady(lobbyId: String, playerName: String) {
        database.child("lobbies").child(lobbyId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val lobby = dataSnapshot.value as HashMap<*, *>?
                if (lobby != null) {
/*                    if (lobby["player1"] == playerName) {
                        database.child("lobbies").child(lobbyId).child("player1Ready").setValue(true)
                    } else if (lobby["player2"] == playerName) {
                        database.child("lobbies").child(lobbyId).child("player2Ready").setValue(true)
                    }*/
                    if (lobby["player1"] == playerName) {
                        database.child("lobbies").child(lobbyId).child("player1Ready").setValue(true)
                    }
                    if (lobby["player2"] == playerName) {
                        database.child("lobbies").child(lobbyId).child("player2Ready").setValue(true)
                    }
                    checkLobbyStatus(lobbyId, lobby)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("", "loadPost:onCancelled", error.toException())
            }
        })
    }

    private fun checkLobbyStatus(lobbyId: String, lobby: HashMap<*, *>) {
        if (lobby["player1Ready"] as Boolean && lobby["player2Ready"] as Boolean) {
            // Both players are ready, start the game
            val player1 = lobby["player1"] as String
            val player2 = lobby["player2"] as String
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ChatFragment.newInstance(lobbyId, player1, player2))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        Firebase.auth.signOut()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }
}