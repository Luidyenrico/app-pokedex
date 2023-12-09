package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.PokeApiResponse
import com.example.pokedex.api.PokeResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Fornece dados relacionados à lista de Pokémon
class PokeListViewModel() : ViewModel() {
    // Conecta com a API do Pokédex
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") // URL base da API
        .addConverterFactory(GsonConverterFactory.create()) // Converte os resultados para objetos
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java) // Criação do serviço da API

    // Armazena a lista de resultados de Pokémon
    val pokemonList = MutableLiveData<List<PokeResult>>()

    // Funcao que pega a lista de Pokémon da API
    fun getPokemonList() {
        val call = service.getPokemonList(151, 0) // Define a quantidade de pokemons e a partir de quando começa a contagem

        // Enfileira a chamada para ser executada de forma assíncrona
        call.enqueue(object : Callback<PokeApiResponse> {
            override fun onResponse(call: Call<PokeApiResponse>, response: Response<PokeApiResponse>) {
                response.body()?.results?.let { list ->
                    pokemonList.postValue(list) // Atualiza com a lista de resultados de Pokémon
                }
            }

            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }
}