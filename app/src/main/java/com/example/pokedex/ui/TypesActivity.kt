package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R

class TypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)


        //Botao de voltar e de batalha que redirecionam o usuario ou de volta ou para a pagina de luta de tipos.

        val botaoRetornar: ImageButton = findViewById(R.id.botaoRetornar)
        val botaoBattle: Button = findViewById(R.id.buttonBattle)

        botaoRetornar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@TypesActivity, InicialActivity::class.java)
                startActivity(intent)
            }
        })

        botaoBattle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@TypesActivity, BattleActivity::class.java)
                startActivity(intent)
            }
        })

    }
}