package com.ifsul.pokemon.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifsul.pokemon.R;
import com.ifsul.pokemon.models.Pokedex;

import java.util.List;

public class PokedexRecyclerAdapter extends RecyclerView.Adapter<PokedexRecyclerAdapter.PokedexHolder> {

    private List<Pokedex> pokedexList;

    public PokedexRecyclerAdapter(List<Pokedex> l){
        this.pokedexList = l;
    }

    @NonNull
    @Override
    public PokedexHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemon, viewGroup, false);

        PokedexHolder pokedexHolder = new PokedexHolder(view);

        return pokedexHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexHolder pokedexHolder, int i) {
        //pokedexList[i];
    }

    @Override
    public int getItemCount() {
        return pokedexList.size();
    }
    public static class PokedexHolder extends RecyclerView.ViewHolder {

        public PokedexHolder(@NonNull View itemView) {
            super(itemView);
            TextView tvNome = itemView.findViewById(R.id.tvNome);
            TextView tvID = itemView.findViewById(R.id.tvID);
            ImageView ivPokemon = itemView.findViewById(R.id.ivPokemon);
            CardView cvPokemon = itemView.findViewById(R.id.cvPokemon);
        }

    }

}
