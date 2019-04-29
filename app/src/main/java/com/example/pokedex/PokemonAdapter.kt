package com.example.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_pokemon.view.*

class PokemonAdapter(var pokemons: List<Pokemon>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.cardview_pokemon, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int): Unit = holder.bind(pokemons[position])

    fun changeList(movies: List<Pokemon>){
        this.pokemons = movies
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Pokemon) = with(itemView){

            Glide.with(itemView.context)
                .load(item.Imagen)
                .placeholder(R.drawable.ic_launcher_background)
                .into(pokemon_image_cv)

            tv_nombre.text = item.Nombre
            tv_tipo.text = item.Tipo
        }
    }
}