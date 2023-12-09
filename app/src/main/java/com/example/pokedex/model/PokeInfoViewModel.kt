package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ViewModel que fornece os dados das informações de Pokémon
class PokeInfoViewModel() : ViewModel() {
    // Configuração do Retrofit que conecta com a API do Pokédex
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    //Armazena as informações do pokemon
    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonDescription = MutableLiveData<Pokemon>()

    // Obtem informaçoes detalhadas do pokemon
    fun getPokemonInfo(id: Int){
        val call = service.getPokemonInfo(id)

        //Executa a chamada de forma assincrona
        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })

        }

    // Obtem a descricao de cada pokemon pelo id
    fun getPokemonDescription(id: Int){
        val callDescription = service.getPokemonSpecies(id)

        //Executa a chamada tambem de forma assincrona
        callDescription.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonDescription.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })
    }
}