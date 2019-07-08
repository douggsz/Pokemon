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
            //inserirPokemon(db);
            inserirPokedex(db);
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

    private void inserirPokedex(DatabasePokemon db) {
        db.inserir_na_pokedex(getApplicationContext(),1, 1, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 2, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 3, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 4, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 5, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 6, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 7, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 8, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 9, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 10, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 11, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 12, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 13, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 14, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 15, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 16, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 17, 2);
        db.inserir_na_pokedex(getApplicationContext(),1, 18, 2);
    }

    private void inserirPokemon(DatabasePokemon db) {
        db.inserir_pokemon(getApplicationContext(),"Groudon", 0, "https://img.pokemondb.net/artwork/vector/large/groudon.png");
        db.inserir_pokemon(getApplicationContext(),"Rayquaza", 1, "https://img.pokemondb.net/artwork/vector/large/rayquaza.png");
        db.inserir_pokemon(getApplicationContext(),"Kyogre", 2, "https://pokemondb.net/pokebase/qa-plugin/https-img-proxy/image.php?key=b5914bd0ba1d0c7f&url=http%3A%2F%2Fpokemondungeon.net%2Fgallery%2Falbums%2FDream%2520World%2FPokemon%2Fnormal_382Kyogre_Dream.png");
        db.inserir_pokemon(getApplicationContext(), "Pikachu", 3, "http://www.pngmart.com/files/2/Pikachu-PNG-Photos.png");
        db.inserir_pokemon(getApplicationContext(), "Sandslash", 4, "https://i2.wp.com/falamedeiros.com.br/wp-content/uploads/2017/06/pokemon-go-quais-vantagens-e-desvantagens-tipo-terrestre.png");
        db.inserir_pokemon(getApplicationContext(), "Clefable", 5, "https://vignette.wikia.nocookie.net/victoryroad/images/2/27/FLArt_036.png/revision/latest?cb=20110609125054");
        db.inserir_pokemon(getApplicationContext(), "Graveler",6 , "https://cdn.bulbagarden.net/upload/thumb/7/75/075Graveler.png/250px-075Graveler.png");
        db.inserir_pokemon(getApplicationContext(), "Doduo", 7, "http://3.bp.blogspot.com/_hfj1R8wnDyg/SkeQ5L79QqI/AAAAAAAAAQY/cxf6qQy5R5U/s320/Farfetch%27d.png");
        db.inserir_pokemon(getApplicationContext(), "Muk", 8, "https://cdn.bulbagarden.net/upload/thumb/7/7c/089Muk.png/250px-089Muk.png");
        db.inserir_pokemon(getApplicationContext(), "Scyther", 9, "http://2.bp.blogspot.com/_hfj1R8wnDyg/Skim14wg3ZI/AAAAAAAAAVo/Iod-KYOGJE8/s400/Scyther.png");
        db.inserir_pokemon(getApplicationContext(), "Sneasel", 10, "https://www.geekno.com/pt/wp-content/uploads/sites/2/2018/10/sneasel-150x150.png");
        db.inserir_pokemon(getApplicationContext(), "Gengar", 11, "https://vignette.wikia.nocookie.net/pokepediabr/images/c/c6/094Gengar.png/revision/latest/scale-to-width-down/180?cb=20161215013207&path-prefix=pt-br");
        db.inserir_pokemon(getApplicationContext(), "Golduck", 12, "https://cdn.bulbagarden.net/upload/thumb/f/fe/055Golduck.png/1200px-055Golduck.png");
        db.inserir_pokemon(getApplicationContext(), "Aerodactyl", 13, "http://3.bp.blogspot.com/_hfj1R8wnDyg/SktbHeukY7I/AAAAAAAAAYI/fxA402nBO7g/s400/Aerodactyl.png");
        db.inserir_pokemon(getApplicationContext(), "Magneton", 14, "http://2.bp.blogspot.com/_hfj1R8wnDyg/SkeMHMdsr4I/AAAAAAAAAQQ/QrFIiD9hYys/s320/Magneton.gif");
        db.inserir_pokemon(getApplicationContext(), "Jinx", 15, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/124.png");
        db.inserir_pokemon(getApplicationContext(), "Mankey", 16, "http://4.bp.blogspot.com/_hfj1R8wnDyg/Skdpt94TxmI/AAAAAAAAAMg/vF2MFvedqVI/s320/Mankey.png");
        db.inserir_pokemon(getApplicationContext(), "Togetic", 17, "https://vignette.wikia.nocookie.net/pokepediabr/images/1/11/176Togetic.png/revision/latest/scale-to-width-down/180?cb=20161215014938&path-prefix=pt-br");
    }
}
