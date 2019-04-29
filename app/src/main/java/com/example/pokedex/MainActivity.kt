package com.example.pokedex


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val  pokemonList: ArrayList<Pokemon> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    fun initRecyclerView(){
        viewManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(pokemonList)

        pokemon_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = pokemonAdapter
        }
        add_pokemon_btn.setOnClickListener{
            FetchPokemon().execute("${pokemon_name_et.text}")
        }
    }

    fun addPokemonToList(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        pokemonAdapter.changeList(pokemonList)
        Log.d("Number", pokemonList.size.toString())
    }

    private inner class FetchPokemon : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String): String {

            if(params.isNullOrEmpty()) return ""

            val pokemonName = params[0]
            val pokemonURL = NetworkUtils().buildSearchUrl(pokemonName)

            return try{
                NetworkUtils().getResponseFromHttpUrl(pokemonURL)
            }catch (e: IOException){
                ""
            }

        }

        override fun onPostExecute(pokemonInfo: String) {
            super.onPostExecute(pokemonInfo)

            if(!pokemonInfo.isEmpty()){
                val pokemonJson = JSONObject(pokemonInfo)

                if(pokemonJson.getString("Response")=="True"){
                    val pokemon = Gson().fromJson<Pokemon>(pokemonInfo, Pokemon::class.java::class.java)
                    addPokemonToList(pokemon)
                }else{
                    Snackbar.make(ll_main, "NO", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}