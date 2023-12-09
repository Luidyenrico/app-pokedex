package com.example.pokedex.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Pega as informações do pokemon pelo id
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    //Define a lista com o limite de pokemons
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>

    //Pega as informações do tipo de pokemon.
    @GET("pokemon-species/{id}")
    fun getPokemonSpecies(@Path("id") id: Int): Call<Pokemon>
}