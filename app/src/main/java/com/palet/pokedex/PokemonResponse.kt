package com.palet.pokedex

import com.google.gson.annotations.SerializedName

class PokemonResponse(@SerializedName("name")var name:String?,
                      @SerializedName("id")var codigo:String?,
                      @SerializedName("weight")var peso:String?,
                      @SerializedName("height")var altura:String?


                      )