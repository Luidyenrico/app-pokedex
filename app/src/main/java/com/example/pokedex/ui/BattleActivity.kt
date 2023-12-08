package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R

class BattleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        val botaoRetornar: ImageButton = findViewById(R.id.botaoRetornar)
        botaoRetornar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(this@BattleActivity, TypesActivity::class.java)
                startActivity(intent)
            }
        })

        val spinner1: Spinner = findViewById(R.id.spinner1)
        val spinner2: Spinner = findViewById(R.id.spinner2)

        // Array de opções para os Spinners
        val opcoes = arrayOf("NORMAL", "FIRE", "GRASS", "WATER", "ELECTRIC", "ICE", "GROUND", "FLYING", "POISON",
            "FIGHTING", "PSYCHIC", "DARK", "ROCK", "BUG", "GHOST", "STEEL", "DRAGON", "FAIRY")

        // Adapter para os Spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Definindo o adapter para os Spinners
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Aqui você pode armazenar a seleção do Spinner 1 se necessário
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Não é necessário implementar nada aqui
            }
        })

        spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Aqui você pode armazenar a seleção do Spinner 2 se necessário
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Não é necessário implementar nada aqui
            }
        })

        // Botão para comparar as seleções e exibir o resultado
        val botaoBatalhar: Button = findViewById(R.id.buttonFight)
        botaoBatalhar.setOnClickListener {
            val selecaoSpinner1 = spinner1.selectedItem.toString()
            val selecaoSpinner2 = spinner2.selectedItem.toString()

            // Verificação de empate
            if (selecaoSpinner1.equals(selecaoSpinner2, ignoreCase = true)) {
                Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
            } else {
                // Determinar o vencedor e exibir o resultado
                val vencedor = determinarVencedor(selecaoSpinner1, selecaoSpinner2)
                exibirResultado(vencedor)
            }
        }
    }

    // Lógica para determinar o vencedor
    private fun determinarVencedor(tipo1: String, tipo2: String): String {
        if (tipo1 == tipo2) {
            return "Empate!"
        }

        return when (tipo1) {
            "FIRE" -> when (tipo2) {
                "WATER", "GRASS" -> "WATER"
                "FLYING" -> "FIRE"
                else -> tipo2
            }
            "WATER" -> when (tipo2) {
                "FIRE", "GROUND" -> "WATER"
                "FLYING" -> "FLYING"
                else -> tipo2
            }
            "GRASS" -> when (tipo2) {
                "FIRE", "GROUND" -> "GRASS"
                "FLYING" -> "FLYING"
                else -> tipo2
            }
            "FLYING" -> when (tipo2) {
                "FIRE" -> "FIRE"
                "WATER", "GRASS" -> "FLYING"
                else -> tipo2
            }
            else -> "Resultado Indefinido"
        }
    }

    // Lógica para exibir o resultado
    private fun exibirResultado(vencedor: String) {
        // Adicione a lógica de exibição de resultado aqui
        // Por enquanto, vamos apenas exibir uma mensagem com o tipo vencedor
        Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_SHORT).show()
    }
}

