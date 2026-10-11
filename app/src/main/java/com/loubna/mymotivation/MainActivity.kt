package com.loubna.mymotivation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Create our list of phrases
        val phrases = listOf(
            "Believe in yourself and anything is possible.",
            "Never stop learning.",
            "Every day is a new opportunity.",
            "Trust the process.",
            "Small steps also take you far.",
            "See problems as opportunities.",
            "Trust your visions.",
            "It's not hard, it's just new.",
            "Focus on progress, not perfection.",
            "Your only limit is your mindset."
        )
        // The Variables
        val textPhrase = findViewById<TextView>(R.id.txtFrase)
        val btnNewPhrase = findViewById< Button>(R.id.btnNuevaFrase)
        val btnFavorite = findViewById<Button>(R.id.btnFavorito)
        val btnViewFavorites = findViewById<Button>(R.id.btnVerFavoritos)
        // List for save the favorite phrases
        val favorits = mutableListOf<String>()

        // Display a random phrase
        textPhrase.text = phrases.random()

        // Button for change the phrase randomly
        btnNewPhrase.setOnClickListener {
            textPhrase.text = phrases.random()

            if (favorits.contains(textPhrase.text.toString())) {
                btnFavorite.text = "♥"
            }else{
                btnFavorite.text = "♡"
            }
        }

        // when the user click the button for save the phrase as a favorite
        btnFavorite.setOnClickListener {
            val phraseActual = textPhrase.text.toString()

            if(!favorits.contains(phraseActual)){
                favorits.add(phraseActual)
                btnFavorite.text = "♥"
            }

            btnViewFavorites.setOnClickListener {
            val message = if (favorits.isEmpty()){
                "You haven't saved any favorites yet."
            }else{
                favorits.joinToString("\n\n")
            }

                AlertDialog.Builder(this)
                    .setTitle("My Favorite Phrases ❤️")
                    .setMessage(message)
                    .setPositiveButton("Close", null)
                    .show()
            }
        }
    }
}