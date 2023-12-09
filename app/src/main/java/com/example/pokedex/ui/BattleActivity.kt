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

        // Opcoes para os Spinners
        val opcoes = arrayOf("NORMAL", "FIRE", "GRASS", "WATER", "ELECTRIC", "ICE", "GROUND", "FLYING", "POISON",
            "FIGHTING", "PSYCHIC", "DARK", "ROCK", "BUG", "GHOST", "STEEL", "DRAGON", "FAIRY")

        // Adapter para os Spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Define o adapter para os Spinners
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        //Grava a informação do spinner1
        spinner1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        //Grava a informação do spinner2
        spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        // Botão para comparar as selecoes e exibir o resultado
        val botaoBatalhar: Button = findViewById(R.id.buttonFight)
        botaoBatalhar.setOnClickListener {
            val selecaoSpinner1 = spinner1.selectedItem.toString()
            val selecaoSpinner2 = spinner2.selectedItem.toString()

            // Verifica o empate
            if (selecaoSpinner1.equals(selecaoSpinner2, ignoreCase = true)) {
                Toast.makeText(this, "DRAW", Toast.LENGTH_SHORT).show()
            } else {
                // Determina o vencedor e exibir o resultado
                val vencedor = determinarVencedor(selecaoSpinner1, selecaoSpinner2)
                exibirResultado(vencedor)
            }
        }

    }

    // Funcao que determina o vencedor pelo tipo de vantagem pokemon
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

    //Pega quais tipos pokemons tem vantagens sobre quais outros
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

    // Exibir o resultado
    private fun exibirResultado(vencedor: String) {
        // Verifica se deu empate
        val mensagem = if (vencedor == "DRAW") {
            "DRAW"
        } else {
            "$vencedor WINS"
        }

        // Exibe a mensagem de vitoria
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

