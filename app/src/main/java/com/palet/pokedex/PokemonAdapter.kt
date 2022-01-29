package com.palet.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(val pokemon:MutableList<Pokemon>):RecyclerView.Adapter<PokemonAdapter.PokeHolder>(){


    class PokeHolder(val view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.tv_pokename)
        //val id   = view.findViewById<TextView>(R.id.tv_id)

        fun bind(pokemons: Pokemon) {
           name.text = pokemons.name
           //id.text   = "NRO: " + pokemons.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listaprincipal, parent, false)
        return PokeHolder(view)

    }

    override fun onBindViewHolder(holder: PokeHolder, position: Int) {
        val pokemons = pokemon[position]
        holder.bind(pokemons)
    }

    override fun getItemCount(): Int {
       return pokemon.size

    }
}