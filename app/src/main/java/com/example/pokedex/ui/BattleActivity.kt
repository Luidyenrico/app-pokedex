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
            "FIGHTING", "PSYCHIC","DARK", "ROCK", "BUG", "GHOST", "STEEL", "DRAGON", "FAIRY")

// Adapter para os Spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// Definindo o adapter para os Spinners
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Aqui você pode armazenar a seleção do Spinner 1
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Não é necessário implementar nada aqui
            }
        })

        spinner2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Aqui você pode armazenar a seleção do Spinner 2
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Não é necessário implementar nada aqui
            }
        })

        // Você pode adicionar um botão ou outra ação para comparar as seleções e exibir o resultado
        val botaoBatalhar: Button = findViewById(R.id.buttonBattle)
        botaoBatalhar.setOnClickListener {
            val selecaoSpinner1 = spinner1.selectedItem.toString()
            val selecaoSpinner2 = spinner2.selectedItem.toString()

            // Aqui você pode comparar as seleções e exibir a imagem do tipo vencedor com uma frase de vitória
            if (selecaoSpinner1 == selecaoSpinner2) {
                Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
            } else {
                // Implemente a lógica para determinar o vencedor e exibir a imagem correspondente
                val vencedor = determinarVencedor(selecaoSpinner1, selecaoSpinner2)
                exibirResultado(vencedor)
            }
        }
    }

    // Implemente a lógica para determinar o vencedor
    private fun determinarVencedor(tipo1: String, tipo2: String): String {
        // Adicione a lógica de comparação de tipos aqui
        // Por enquanto, vamos apenas retornar o tipo do Spinner 1 como vencedor
        return tipo1
    }

    // Implemente a lógica para exibir o resultado (imagem do tipo vencedor com uma frase de vitória)
    private fun exibirResultado(vencedor: String) {
        // Adicione a lógica de exibição de resultado aqui
        // Por enquanto, vamos apenas exibir uma mensagem com o tipo vencedor
        Toast.makeText(this, "Vencedor: $vencedor", Toast.LENGTH_SHORT).show()
    }
}
