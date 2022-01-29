package com.palet.pokedex

import android.app.Activity
import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.palet.pokedex.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var retrofit: Retrofit
    private lateinit var rv_listaPrincipal: RecyclerView
    private lateinit var tvNombrePokemon: TextView
    private lateinit var tvID:TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var tvPeso: TextView
    private lateinit var imgPokemon: ImageView
    private lateinit var tvAltura: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svPokemons.setOnQueryTextListener(this)
        
        //Aqui identifico los elementos del Binding
        tvID = binding.tvIdPokemon
        tvNombrePokemon = binding.tvPokenombre
        tvPeso = binding.tvPeso
        tvAltura = binding.tvAltura
        imgPokemon = binding.imgPokemon
    }


    //cargar el retrofit
    fun getRetroFit():Retrofit{
            return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //metodo para buscar por nombre
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetroFit().create(APIService::class.java).getPokemons("$query")
            val pokemons = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    //aca obtengo los valores a traves del API service
                    val nombre = pokemons?.name
                    val codigo = pokemons?.codigo
                    val peso   = pokemons?.peso
                    val altura = pokemons?.altura
                    putImage(codigo)
                    //asignacion de los valores
                    tvID.text = "Nro " + codigo
                    tvNombrePokemon.text = nombre
                    tvPeso.text = "Peso: " + peso
                    tvAltura.text = "Altura: " + altura

                }
            }

        }
    }

    private fun putImage(codigo: String?) {
        val imagePath = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        Picasso.get().load(imagePath + codigo + ".png").fit().into(imgPokemon)
    }


    //al introducir el nombre, llamamos al metodo de busqueda
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
       return true
    }


}