package com.ifsul.pokemon;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ifsul.pokemon.Adapter.PokedexListAdapter;
import com.ifsul.pokemon.database.DatabasePokemon;
import com.ifsul.pokemon.models.Pokedex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ifsul.pokemon.Utils.constants.DB_NAME;
import static com.ifsul.pokemon.Utils.constants.DB_VERSION;
import static com.ifsul.pokemon.Utils.constants.PREF_NAME;
import static com.ifsul.pokemon.Utils.constants.USUARIO_LOGADO;

public class MainActivity extends AppCompatActivity {

    private DatabasePokemon db;

    // TODO: ACTIVITY SIGN UP
    private String cla = "";
    private int carisma = 0;
    private int inteligencia = 0;
    private int sorte = 0;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabasePokemon(getApplicationContext(), DB_NAME, DB_VERSION);

        verificaUsuarioLogado();

    }

    private void verificaUsuarioLogado() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        id = prefs.getInt(USUARIO_LOGADO, 0);

        if (id == 0) {
            activitySignIn();
        } else {
            activityMenu();
        }
    }

    private void activityPokedex(int id) {
        setContentView(R.layout.activity_pokedex);

        GridView gvPokedex = findViewById(R.id.gvPokedex);
        ArrayList<Pokedex> pokedexList;
        pokedexList = new ArrayList<>();
        pokedexList = db.listar_pokedex(id);
        PokedexListAdapter adapter = new PokedexListAdapter(this, pokedexList);
        gvPokedex.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void activityMapa() {
        setContentView(R.layout.activity_mapa);
    }

    private void activityLoja() {
        setContentView(R.layout.activity_loja);
    }

    private void activityPokemons() {
        setContentView(R.layout.activity_pokemons);
    }

    private void activityMenu() {
        setContentView(R.layout.activity_menu);

        Button btnSair = findViewById(R.id.btnSair);
        CardView cvPokedex = findViewById(R.id.cvPokedex);
        CardView cvLoja = findViewById(R.id.cvLoja);
        CardView cvMapa = findViewById(R.id.cvMapa);
        CardView cvPokemons = findViewById(R.id.cvPokemons);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
                editor.putInt(USUARIO_LOGADO, 0);
                editor.apply();
                editor.commit();
                activitySignIn();
                showToast(getString(R.string.ate_a_proxima));
//                AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
//                dialog.setTitle(getString(R.string.deslogar))
//                        .setMessage(getString(R.string.deslogar_msg))
//                        .setPositiveButton(getString(R.string.confirmar), new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        })
//                        .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        })
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .show();
            }
        });

        cvPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPokedex(id);
            }
        });

        cvLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityLoja();
            }
        });

        cvMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMapa();
            }
        });

        cvPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityPokemons();
            }
        });

    }

    private void activitySignIn() {
        setContentView(R.layout.activity_sign_in);

        final TextInputEditText etEmail = findViewById(R.id.etEmail);
        final TextInputEditText etSenha = findViewById(R.id.etSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitySignUp();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

                if (email.isEmpty()) {
                    showToast(getString(R.string.preencha_email));
                } else if (!isEmailValid(email)) {
                    showToast(getString(R.string.preencha_email_valido));
                } else if (senha.isEmpty()) {
                    showToast(getString(R.string.preencha_senha));
                } else if (senha.length() < 6) {
                    showToast(getString(R.string.tamanho_senha));
                } else {
                    try {
                        if (db.login_jogador(getApplicationContext(), email, senha)) {
                            showToast(getString(R.string.logado_sucesso));
                            activityMenu();
                        } else {
                            showToast(getString(R.string.email_senha_incorreto));
                        }
                    } catch (Exception e) {
                        showToast(e.getMessage());
                    }
                }


            }
        });

    }

    private void activitySignUp() {
        setContentView(R.layout.activity_sign_up);

        final TextInputEditText etNome = findViewById(R.id.etNome);
        final TextInputEditText etApelido = findViewById(R.id.etAppelido);
        final TextInputEditText etEmail = findViewById(R.id.etEmail);
        final TextInputEditText etSenha = findViewById(R.id.etSenha);
        final TextInputEditText etConfirmaSenha = findViewById(R.id.etConfirmaSenha);
        final Button btnTADS = findViewById(R.id.btnTADS);
        final Button btnTAI = findViewById(R.id.btnTAI);
        final Button btnTINF = findViewById(R.id.btnTINF);
        final Button btnTCA = findViewById(R.id.btnTCA);
        SeekBar spCarisma = findViewById(R.id.spCarisma);
        SeekBar spInteligencia = findViewById(R.id.spInteligencia);
        SeekBar spSorte = findViewById(R.id.spSorte);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = etNome.getText().toString().trim();
                String apelido = etApelido.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();
                String confirmaSenha = etConfirmaSenha.getText().toString().trim();

                if (nome.isEmpty()) {
                    showToast(getString(R.string.preencha_nome));
                } else if (apelido.isEmpty()) {
                    showToast(getString(R.string.preencha_apelido));
                } else if (email.isEmpty()) {
                    showToast(getString(R.string.preencha_email));
                } else if (!isEmailValid(email)) {
                    showToast(getString(R.string.preencha_email_valido));
                } else if (senha.isEmpty()) {
                    showToast(getString(R.string.preencha_senha));
                } else if (senha.length() < 6) {
                    showToast(getString(R.string.tamanho_senha));
                } else if (confirmaSenha.isEmpty()) {
                    showToast(getString(R.string.confirma_senha));
                } else if (!senha.equals(confirmaSenha)) {
                    showToast(getString(R.string.senhas_diferentes));
                } else if (cla.isEmpty()) {
                    showToast(getString(R.string.escolha_cla));
                } else if (carisma == 0) {
                    showToast(getString(R.string.escolha_carisma));
                } else if (inteligencia == 0) {
                    showToast(getString(R.string.escolha_inteligencia));
                } else if (sorte == 0) {
                    showToast(getString(R.string.escolha_sorte));
                } else {
                    try {
                        switch (db.inserir_jogador(getApplicationContext(), nome, apelido, email, senha, carisma, sorte, inteligencia, 0.00, cla)) {
                            case 200:
                                activityMenu();
                                break;
                            case 301:
                                showToast("Este Apelido já esta sendo usado");
                                break;
                            case 302:
                                showToast("Este Email já esta sendo usado");
                                break;
                            case 404:
                                showToast("Algo de errado aconteceu, Tente Novamente!");
                                break;
                        }
                    } catch (Exception e) {
                        showToast(e.getMessage());
                    }
                }

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activitySignIn();
            }
        });

        spCarisma.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                carisma = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spInteligencia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                inteligencia = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spSorte.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sorte = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnTADS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cla = "TADS";
                btnTADS.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorAccent));
                btnTAI.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTINF.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTCA.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
            }
        });

        btnTAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cla = "TAI";
                btnTADS.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTAI.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorAccent));
                btnTINF.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTCA.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
            }
        });

        btnTINF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cla = "TINF";
                btnTADS.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTAI.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTINF.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorAccent));
                btnTCA.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
            }
        });

        btnTCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cla = "TCA";
                btnTADS.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTAI.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTINF.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorPrimaryDark));
                btnTCA.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.colorAccent));
            }
        });


    }

    private void inserirPokedex(DatabasePokemon db) {
        db.inserir_na_pokedex(getApplicationContext(), 1, 1, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 2, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 3, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 4, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 5, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 6, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 7, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 8, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 9, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 10, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 11, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 12, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 13, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 14, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 15, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 16, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 17, 2);
        db.inserir_na_pokedex(getApplicationContext(), 1, 18, 2);
    }

    private void inserirPokemon(DatabasePokemon db) {
        db.inserir_pokemon(getApplicationContext(), "Groudon", 0, "https://img.pokemondb.net/artwork/vector/large/groudon.png");
        db.inserir_pokemon(getApplicationContext(), "Rayquaza", 1, "https://img.pokemondb.net/artwork/vector/large/rayquaza.png");
        db.inserir_pokemon(getApplicationContext(), "Kyogre", 2, "https://pokemondb.net/pokebase/qa-plugin/https-img-proxy/image.php?key=b5914bd0ba1d0c7f&url=http%3A%2F%2Fpokemondungeon.net%2Fgallery%2Falbums%2FDream%2520World%2FPokemon%2Fnormal_382Kyogre_Dream.png");
        db.inserir_pokemon(getApplicationContext(), "Pikachu", 3, "https://i.imgur.com/Oo0OFrz.png");
        db.inserir_pokemon(getApplicationContext(), "Sandslash", 4, "https://i2.wp.com/falamedeiros.com.br/wp-content/uploads/2017/06/pokemon-go-quais-vantagens-e-desvantagens-tipo-terrestre.png");
        db.inserir_pokemon(getApplicationContext(), "Clefable", 5, "https://vignette.wikia.nocookie.net/victoryroad/images/2/27/FLArt_036.png/revision/latest?cb=20110609125054");
        db.inserir_pokemon(getApplicationContext(), "Graveler", 6, "https://cdn.bulbagarden.net/upload/thumb/7/75/075Graveler.png/250px-075Graveler.png");
        db.inserir_pokemon(getApplicationContext(), "Doduo", 7, "https://i.imgur.com/rCMOqZD.png");
        db.inserir_pokemon(getApplicationContext(), "Muk", 8, "https://cdn.bulbagarden.net/upload/thumb/7/7c/089Muk.png/250px-089Muk.png");
        db.inserir_pokemon(getApplicationContext(), "Scyther", 9, "https://i.imgur.com/PRpGmvm.png");
        db.inserir_pokemon(getApplicationContext(), "Sneasel", 10, "https://www.geekno.com/pt/wp-content/uploads/sites/2/2018/10/sneasel-150x150.png");
        db.inserir_pokemon(getApplicationContext(), "Gengar", 11, "https://vignette.wikia.nocookie.net/pokepediabr/images/c/c6/094Gengar.png/revision/latest/scale-to-width-down/180?cb=20161215013207&path-prefix=pt-br");
        db.inserir_pokemon(getApplicationContext(), "Golduck", 12, "https://cdn.bulbagarden.net/upload/thumb/f/fe/055Golduck.png/1200px-055Golduck.png");
        db.inserir_pokemon(getApplicationContext(), "Aerodactyl", 13, "http://3.bp.blogspot.com/_hfj1R8wnDyg/SktbHeukY7I/AAAAAAAAAYI/fxA402nBO7g/s400/Aerodactyl.png");
        db.inserir_pokemon(getApplicationContext(), "Magneton", 14, "http://2.bp.blogspot.com/_hfj1R8wnDyg/SkeMHMdsr4I/AAAAAAAAAQQ/QrFIiD9hYys/s320/Magneton.gif");
        db.inserir_pokemon(getApplicationContext(), "Jinx", 15, "https://assets.pokemon.com/assets/cms2/img/pokedex/full/124.png");
        db.inserir_pokemon(getApplicationContext(), "Mankey", 16, "http://4.bp.blogspot.com/_hfj1R8wnDyg/Skdpt94TxmI/AAAAAAAAAMg/vF2MFvedqVI/s320/Mankey.png");
        db.inserir_pokemon(getApplicationContext(), "Togetic", 17, "https://vignette.wikia.nocookie.net/pokepediabr/images/1/11/176Togetic.png/revision/latest/scale-to-width-down/180?cb=20161215014938&path-prefix=pt-br");
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

}
