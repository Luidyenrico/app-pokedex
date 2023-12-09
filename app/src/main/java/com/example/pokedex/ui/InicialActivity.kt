package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R

class InicialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        //Captura os botoes
        val botaoPokedex = findViewById<Button>(R.id.botaoPokedex)
        val botaoTypes = findViewById<Button>(R.id.botaoTypes)

        botaoPokedex.setOnClickListener {
            // Redireciona para a pokedex
            val intent = Intent(this, PokemonList::class.java)
            startActivity(intent)
        }

        botaoTypes.setOnClickListener {
            // Redireciona para a aba de tipos
            val intent = Intent(this, TypesActivity::class.java)
            startActivity(intent)
        }
    }
}