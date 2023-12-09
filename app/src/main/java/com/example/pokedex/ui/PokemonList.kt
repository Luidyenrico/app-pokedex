package com.example.pokedex.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.model.PokeListAdapter
import com.example.pokedex.model.PokeListViewModel
import com.example.pokedex.databinding.ActivityMainBinding

class PokemonList : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokeListAdapter: PokeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializa o viewmodel
        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        initUI()
    }

    private fun initUI() {
        //Layout da recyclerview
        binding.pokelistRecyclerView.layoutManager = LinearLayoutManager(this)


        pokeListAdapter = PokeListAdapter{
            //Permite clicar nos itens da lista
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }


        binding.pokelistRecyclerView.adapter = pokeListAdapter

        viewModel.getPokemonList()

        //Atualiza a lista com novos resultados a medida que passa a tela
        viewModel.pokemonList.observe(this, Observer { list ->
            pokeListAdapter.setData(list)
        })
        //Botao de voltar
        binding.botaoRetornar.setOnClickListener {
            val intent = Intent(this, InicialActivity::class.java)
            startActivity(intent)
        }
    }
}
