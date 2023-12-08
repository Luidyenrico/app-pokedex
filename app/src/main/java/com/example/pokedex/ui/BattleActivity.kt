package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.R

class BattleActivity : AppCompatActivity() {

    private lateinit var resultadoImagem: ImageView
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
        resultadoImagem = findViewById(R.id.resultadoImagem)

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
                Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show()
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
            return "DRAW"
        }

        val vantagensTipo1 = obterVantagens(tipo1)
        val vantagensTipo2 = obterVantagens(tipo2)

        if (vantagensTipo1.contains(tipo2)) {
            return tipo1
        } else if (vantagensTipo2.contains(tipo1)) {
            return tipo2
        } else {
            return "DRAW"
        }
    }

    private fun obterVantagens(tipo: String): List<String> {
        return when (tipo) {
            "NORMAL" -> emptyList()
            "GRASS" -> listOf("GROUND", "ROCK", "WATER")
            "FIRE" -> listOf("BUG", "GRASS", "ICE", "STEEL")
            "WATER" -> listOf("FIRE", "GROUND", "ROCK")
            "ELECTRIC" -> listOf("WATER", "FLYING")
            "FLYING" -> listOf("BUG", "FIGHTING", "GRASS")
            "ICE" -> listOf("DRAGON", "FLYING", "GRASS", "GROUND")
            "ROCK" -> listOf("BUG", "FIRE", "FLYING", "ICE")
            "GROUND" -> listOf("ELECTRIC", "FIRE", "POISON", "ROCK", "STEEL")
            "STEEL" -> listOf("FAIRY", "ICE", "ROCK", "POISON")
            "FIGHTING" -> listOf("DARK", "ICE", "NORMAL", "ROCK", "STEEL")
            "DARK" -> listOf("GHOST", "PSYCHIC")
            "PSYCHIC" -> listOf("FIGHTING", "POISON")
            "POISON" -> listOf("FAIRY", "GRASS")
            "BUG" -> listOf("DARK", "GRASS", "PSYCHIC")
            "FAIRY" -> listOf("DARK", "DRAGON", "FIGHTING")
            "GHOST" -> listOf("PSYCHIC")
            "DRAGON" -> emptyList()
            else -> emptyList()
        }
    }

    // Lógica para exibir o resultado
    private fun exibirResultado(vencedor: String) {
        // Verifica se o vencedor é "DRAW"
        val mensagem = if (vencedor == "DRAW") {
            "DRAW"
        } else {
            "$vencedor WINS"
        }

        // Exibe a mensagem
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()

        if (vencedor != "DRAW") {
            val resourceId = resources.getIdentifier("${vencedor.lowercase()}_img", "drawable", packageName)
            resultadoImagem.setImageResource(resourceId)
            resultadoImagem.visibility = View.VISIBLE
        } else {
            resultadoImagem.visibility = View.INVISIBLE
        }
    }
}

