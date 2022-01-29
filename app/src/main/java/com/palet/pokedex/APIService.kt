package com.palet.pokedex

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPokemons(@Url url:String):Response<PokemonResponse>
}