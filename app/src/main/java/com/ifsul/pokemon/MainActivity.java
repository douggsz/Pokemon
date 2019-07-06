package com.ifsul.pokemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.ifsul.pokemon.Adapter.PokedexListAdapter;
import com.ifsul.pokemon.database.DatabasePokemon;
import com.ifsul.pokemon.models.Pokedex;

import java.util.ArrayList;

import static com.ifsul.pokemon.Utils.constants.DB_NAME;
import static com.ifsul.pokemon.Utils.constants.DB_VERSION;

public class MainActivity extends AppCompatActivity {

    private GridView gvPokedex;
    private PokedexListAdapter adapter;
    private ArrayList<Pokedex> pokedexList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabasePokemon db = new DatabasePokemon(getApplicationContext(), DB_NAME, DB_VERSION);

        // TODO: DESCOMENTAR E RODAR UMA VEZ PARA ALIMENTAR O DB
        try {
//            db.inserir_pokemon(getApplicationContext(),"Groudon", 0, "https://img.pokemondb.net/artwork/vector/large/groudon.png");
//            db.inserir_pokemon(getApplicationContext(),"Rayquaza", 1, "https://img.pokemondb.net/artwork/vector/large/rayquaza.png");
//            db.inserir_pokemon(getApplicationContext(),"Kyogre", 2, "https://pokemondb.net/pokebase/qa-plugin/https-img-proxy/image.php?key=b5914bd0ba1d0c7f&url=http%3A%2F%2Fpokemondungeon.net%2Fgallery%2Falbums%2FDream%2520World%2FPokemon%2Fnormal_382Kyogre_Dream.png");
//            db.inserir_na_pokedex(getApplicationContext(),1, 1, 2);
//            db.inserir_na_pokedex(getApplicationContext(),1, 2, 2);
//            db.inserir_na_pokedex(getApplicationContext(),1, 3, 2);
        } catch (Exception e) {
            Log.e("PKDB", e.getMessage());
        }

        gvPokedex = findViewById(R.id.gvPokedex);
        pokedexList = new ArrayList<>();
        pokedexList = db.listar_pokedex(1);
        adapter = new PokedexListAdapter(this, pokedexList);
        gvPokedex.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
