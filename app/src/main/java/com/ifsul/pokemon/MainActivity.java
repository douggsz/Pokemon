package com.ifsul.pokemon;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.ifsul.pokemon.Adapter.PokedexListAdapter;
import com.ifsul.pokemon.database.DatabasePokemon;
import com.ifsul.pokemon.models.Pokedex;

import java.util.ArrayList;
import java.util.List;

import static com.ifsul.pokemon.Utils.constants.DB_NAME;
import static com.ifsul.pokemon.Utils.constants.DB_VERSION;

public class MainActivity extends AppCompatActivity {

    private GridView gvPokedex;
    private PokedexListAdapter adapter;
    private List<Pokedex> pokedexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabasePokemon db = new DatabasePokemon(getApplicationContext(), DB_NAME, DB_VERSION);

        try {
            db.inserir_na_pokedex(1, 0, 2);
            db.inserir_na_pokedex(1, 1, 2);
            db.inserir_na_pokedex(1, 2, 2);
        } catch (Exception e) {
            Log.d("PKDB", e.getMessage());
        }

        gvPokedex = findViewById(R.id.gvPokedex);
        pokedexList = new ArrayList<>();

        adapter = new PokedexListAdapter(MainActivity.this, pokedexList);

        gvPokedex.setAdapter(adapter);

        pokedexList = db.listar_pokedex(1);
        adapter.notifyDataSetChanged();

    }
}
