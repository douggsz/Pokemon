package com.ifsul.pokemon.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ifsul.pokemon.R;
import com.ifsul.pokemon.models.Pokedex;
import com.ifsul.pokemon.models.Pokemon;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class PokedexListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pokedex> pokedexList;

    public PokedexListAdapter(Context c, ArrayList<Pokedex> l) {
        this.context = c;
        this.pokedexList = l;
    }

    @Override
    public int getCount() {
        return pokedexList.size();
    }

    @Override
    public Object getItem(int i) {
        return pokedexList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = null;

        Pokedex pokedex = (Pokedex) getItem(i);

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        if (view == null) {
            v = inflater.inflate(R.layout.pokemon, null);
            Pokemon pokemon = pokedex.getPokemon();

            TextView tvNome = v.findViewById(R.id.tvNome);
            TextView tvID = v.findViewById(R.id.tvID);
            ImageView ivPokemon = v.findViewById(R.id.ivPokemon);
            CardView cvPokemon = v.findViewById(R.id.cvPokemon);
            final ProgressBar pbPokemon = v.findViewById(R.id.pbPokemon);

            tvNome.setText(pokemon.getNome());
            tvID.setText("#0" + pokedex.getId());
            Picasso.with(v.getContext())
                    .load(pokemon.getImagem())
                    .into(ivPokemon, new Callback() {
                        @Override
                        public void onSuccess() {
                            pbPokemon.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {

                        }
                    });
            cvPokemon.setCardBackgroundColor(corTipoPokemon(pokemon.getTipo()));

        } else {
            v = view;
        }

        return v;
    }

    private int corTipoPokemon(int tipo) {
        int result = 0;
        switch (tipo) {
            case 0:
                result = context.getColor(R.color.colorFogo);
                break;
            case 1:
                result = context.getColor(R.color.colorAgua);
                break;
            case 2:
                result = context.getColor(R.color.colorPlanta);
                break;
            case 3:
                result = context.getColor(R.color.colorEletrico);
                break;
            case 4:
                result = context.getColor(R.color.colorTerrestre);
                break;
            case 5:
                result = context.getColor(R.color.colorNormal);
                break;
            case 6:
                result = context.getColor(R.color.colorPedra);
                break;
            case 7:
                result = context.getColor(R.color.colorVoador);
                break;
            case 8:
                result = context.getColor(R.color.colorVenenoso);
                break;
            case 9:
                result = context.getColor(R.color.colorInseto);
                break;
            case 10:
                result = context.getColor(R.color.colorNoturno);
                break;
            case 11:
                result = context.getColor(R.color.colorFantasma);
                break;
            case 12:
                result = context.getColor(R.color.colorPsiquico);
                break;
            case 13:
                result = context.getColor(R.color.colorDragao);
                break;
            case 14:
                result = context.getColor(R.color.colorMetalico);
                break;
            case 15:
                result = context.getColor(R.color.colorGelo);
                break;
            case 16:
                result = context.getColor(R.color.colorLutador);
                break;
            case 17:
                result = context.getColor(R.color.colorFada);
                break;
        }
        return result;
    }

}
