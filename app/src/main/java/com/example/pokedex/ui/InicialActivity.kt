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

        val botaoPokedex = findViewById<Button>(R.id.botaoPokedex)
        val botaoTypes = findViewById<Button>(R.id.botaoTypes)

        botaoPokedex.setOnClickListener {
            val intent = Intent(this, PokemonList::class.java)
            startActivity(intent)
        }

        botaoTypes.setOnClickListener {
            // Redirecionar para a SegundaOpcaoActivity
            val intent = Intent(this, TypesActivity::class.java)
            startActivity(intent)
        }
    }
}