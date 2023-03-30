package com.example.heretochess.model.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRepository {
    private val mainRef = FirebaseDatabase.getInstance().reference
    private val lobbiesRef = FirebaseDatabase.getInstance().getReference("lobbies")
    private var gameId: String? = null
    private var playerId: String? = null
    init {
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener {
            playerId = it.result.user?.uid
        }
        lobbiesRef.child("$gameId").child("black").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                startGame()
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun createLobby(lobbyName: String){
        gameId = lobbyName
        val lobby = hashMapOf<String, Any?>(
            "white" to playerId,
            "black" to null
        )

        FirebaseDatabase.getInstance().getReference("lobbies").
        child(lobbyName).
        setValue(lobby)
    }

    fun joinLobby(lobbyName: String){
        lobbiesRef.child(lobbyName).child("black").setValue(playerId)
    }

    fun completeLobby(){
        mainRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child("players").hasChildren()) {
                    Log.d("", "players:${snapshot.child("players").children.first()}")
                    val firstPlayer = snapshot.child("players").children.first().key
                    val lobby = hashMapOf<String, Any?>(
                        "white" to firstPlayer,
                        "black" to playerId
                    )
                    mainRef.child("players").child(firstPlayer!!).removeValue()
                    lobbiesRef.push().setValue(lobby)
                } else {
//                    val lobbyId = lobbiesRef.push().key
//check nullPointer
//                    gameId = lobbyId
//                    createLobby(lobbyId!!)
                    mainRef.child("players").child(playerId!!).setValue(true)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("", "error: $error")
            }

        })
    }
    private fun startGame() {

    }
    fun getGameId() = gameId
}
