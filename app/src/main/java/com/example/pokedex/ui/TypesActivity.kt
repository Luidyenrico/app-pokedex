package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R

class TypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_types)

        val botaoRetornar: ImageButton = findViewById(R.id.botaoRetornar)

        botaoRetornar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@TypesActivity, InicialActivity::class.java)
                startActivity(intent)
            }
        })
    }
}