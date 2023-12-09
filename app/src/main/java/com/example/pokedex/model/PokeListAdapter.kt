package com.example.pokedex.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.pokedex.api.PokeResult
import com.example.pokedex.databinding.PokeListBinding

class PokeListAdapter(private val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {
    //Lista de pokemons
    private var pokemonList: List<PokeResult> = emptyList<PokeResult>()


    //Define os dados da lista
    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    //Funcao que chama a Recycler view e infla com novos viewholders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    // Retorna o numero de itens da lista
    override fun getItemCount(): Int {
        return pokemonList.size
    }

    // Funcao para exibir os dados
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val binding = holder.binding
        val pokemon = pokemonList[position]

        //Exibe numero e nome do pokemon na lista
        binding.pokemonText.text = "#${position + 1} - ${pokemon.name}"

        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    class SearchViewHolder(val binding: PokeListBinding): RecyclerView.ViewHolder(binding.root)
}
