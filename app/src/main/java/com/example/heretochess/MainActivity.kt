package com.example.heretochess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.heretochess.ui.main.MainFragment
import com.example.heretochess.ui.main.StartFragment
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StartFragment.newInstance())
                .commitNow()
        }
    }

    fun saveChatId(chatId: String){

    }

    override fun onDestroy() {
        FirebaseDatabase.getInstance().getReference()
        super.onDestroy()
    }
}