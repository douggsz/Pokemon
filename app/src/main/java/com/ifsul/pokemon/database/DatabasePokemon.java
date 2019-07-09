package com.ifsul.pokemon.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ifsul.pokemon.models.Jogador;
import com.ifsul.pokemon.models.Pokedex;
import com.ifsul.pokemon.models.Pokemon;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.ifsul.pokemon.Utils.constants.APELIDO;
import static com.ifsul.pokemon.Utils.constants.CARISMA;
import static com.ifsul.pokemon.Utils.constants.CLA;
import static com.ifsul.pokemon.Utils.constants.DINHEIRO;
import static com.ifsul.pokemon.Utils.constants.EMAIL;
import static com.ifsul.pokemon.Utils.constants.ID;
import static com.ifsul.pokemon.Utils.constants.ID_JOGADOR;
import static com.ifsul.pokemon.Utils.constants.ID_POKEMON;
import static com.ifsul.pokemon.Utils.constants.ID_POKEMON_JOGADOR;
import static com.ifsul.pokemon.Utils.constants.IMAGEM;
import static com.ifsul.pokemon.Utils.constants.INTELIGENCIA;
import static com.ifsul.pokemon.Utils.constants.JOGADOR;
import static com.ifsul.pokemon.Utils.constants.NOME;
import static com.ifsul.pokemon.Utils.constants.POKEDEX;
import static com.ifsul.pokemon.Utils.constants.POKEMON;
import static com.ifsul.pokemon.Utils.constants.PREF_NAME;
import static com.ifsul.pokemon.Utils.constants.QTD_VISTO;
import static com.ifsul.pokemon.Utils.constants.SENHA;
import static com.ifsul.pokemon.Utils.constants.SORTE;
import static com.ifsul.pokemon.Utils.constants.TIPO;
import static com.ifsul.pokemon.Utils.constants.USUARIO_LOGADO;

public class DatabasePokemon extends SQLiteOpenHelper {

    private Jogador jogador;

    public DatabasePokemon(Context context, String nome, int versao) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Cria tabela Pokedex
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "  %s INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", %s INTEGER NOT NULL" +
                ", %s INTEGER NOT NULL" +
                ", %s INTEGER)", POKEDEX, ID, ID_POKEMON, ID_JOGADOR, QTD_VISTO));

        // Cria tabela Pokemon
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "  %s INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", %s VARCHAR(50) NOT NULL" +
                ", %s INTEGER NOT NULL" +
                ", %s VARCHAR(100))", POKEMON, ID, NOME, TIPO, IMAGEM));

        // Cria a tabela Jogador
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "  %s INTEGER PRIMARY KEY autoincrement" +
                ", %s INTEGER" +
                ", %s varchar(20) NOT NULL" +
                ", %s varchar(20) NOT NULL" +
                ", %s varchar(100) NOT NULL" +
                ", %s VARCHAR(20) NOT NULL" +
                ", %s INTEGER NOT NULL" +
                ", %s INTEGER NOT NULL" +
                ", %s INTEGER NOT NULL" +
                ", %s DOUBLE NOT NULL" +
                ", %s VARCHAR(5) NOT NULL);", JOGADOR, ID, ID_POKEMON_JOGADOR, NOME, APELIDO, EMAIL, SENHA, CARISMA, SORTE, INTELIGENCIA, DINHEIRO, CLA));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Pokedex> listar_pokedex(int id_jogador) {
        Pokedex pokedex;
        Pokemon pokemon;
        ArrayList<Pokedex> resultPokedex = new ArrayList<>();

        Cursor cursorPokedex = getWritableDatabase().query(POKEDEX,
                new String[]{ID, ID_POKEMON, ID_JOGADOR, QTD_VISTO}, String.format("%s = %s", ID_JOGADOR, id_jogador), null, null, null, ID);

        cursorPokedex.moveToFirst();
        if (cursorPokedex.getCount() > 0) {
            do {
                pokedex = new Pokedex();
                Cursor cursorPokemon = getWritableDatabase().query(POKEMON,
                        new String[]{ID, NOME, TIPO, IMAGEM}, String.format("%s = %s", ID, cursorPokedex.getInt(1)), null, null, null, null);
                pokemon = new Pokemon();
                if (cursorPokemon.getCount() > 0) {

                    cursorPokemon.moveToFirst();
                    pokemon.setId(cursorPokemon.getInt(0));
                    pokemon.setNome(cursorPokemon.getString(1));
                    pokemon.setTipo(cursorPokemon.getInt(2));
                    pokemon.setImagem(cursorPokemon.getString(3));
                }

                cursorPokemon.close();

                pokedex.setId(cursorPokedex.getInt(0));
                pokedex.setPokemon(pokemon);
                pokedex.setIdJogador(cursorPokedex.getInt(2));
                pokedex.setQtdVisto(cursorPokedex.getInt(3));

                Log.d("ASD", String.format("IDPokedex: %s \n IDPokemon: %s \n Nome: %s", pokedex.getId(), pokemon.getId(), pokemon.getNome()));

                resultPokedex.add(pokedex);

            } while (cursorPokedex.moveToNext());
        }

        cursorPokedex.close();

        return resultPokedex;
    }

    public boolean inserir_pokemon(Context context, String nome, int tipo, String imagem) {
        try {
            ContentValues values = new ContentValues();
            values.put(NOME, nome);
            values.put(TIPO, tipo);
            values.put(IMAGEM, imagem);
            getWritableDatabase().insert(POKEMON, null, values);
            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean inserir_na_pokedex(Context context, int id_jogador, int id_pokemon, int qtd_visto) {
        try {
            ContentValues values = new ContentValues();
            values.put(ID_JOGADOR, id_jogador);
            values.put(ID_POKEMON, id_pokemon);
            values.put(QTD_VISTO, qtd_visto);
            getWritableDatabase().insert(POKEDEX, null, values);
            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean remover_da_pokedex(Context context, int id) {
        try {
            String where = String.format("%s = %s", ID, id);
            getReadableDatabase().delete(POKEDEX, where, null);
            close();
            return true;
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public int inserir_jogador(Context context, String nome, String apelido, String email, String senha, int carisma, int sorte, int inteligencia, double dinheiro, String cla) {
        try {

            Cursor cursorApelido = getWritableDatabase().query(JOGADOR, new String[]{APELIDO}, String.format("%s = '%s'", APELIDO, apelido), null, null, null, null);

            if (cursorApelido.getCount() > 0) {
                cursorApelido.close();
                return 301;
            } else {

                Cursor cursorEmail = getWritableDatabase().query(JOGADOR, new String[]{EMAIL}, String.format("%s = '%s'", EMAIL, email), null, null, null, null);

                if (cursorEmail.getCount() > 0) {
                    cursorEmail.close();
                    return 302;
                } else {

                    ContentValues values = new ContentValues();
                    values.put(NOME, nome);
                    values.put(APELIDO, apelido);
                    values.put(EMAIL, email);
                    values.put(SENHA, senha);
                    values.put(CARISMA, carisma);
                    values.put(SORTE, sorte);
                    values.put(INTELIGENCIA, inteligencia);
                    values.put(DINHEIRO, dinheiro);
                    values.put(CLA, cla);
                    long id = getWritableDatabase().insert(JOGADOR, null, values);
                    salvarLoginShared(context, (int) id);

                    return 200;
                }

            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return 404;
        }
    }

    public Jogador dados_jogador(int id_jogador) {
        Cursor cursorJogador = getWritableDatabase().query(JOGADOR, new String[]{ID, ID_POKEMON_JOGADOR, NOME, APELIDO, EMAIL, SENHA, CARISMA, INTELIGENCIA, SORTE, DINHEIRO, CLA}, String.format("%s = %s", ID, id_jogador), null, null, null, null);

        if (cursorJogador.getCount() > 0) {
            jogador = new Jogador();
            jogador.setId(cursorJogador.getInt(0));
            jogador.setId_pokemon_jogador(cursorJogador.getInt(1));
            jogador.setNome(cursorJogador.getString(2));
            jogador.setApelido(cursorJogador.getString(3));
            jogador.setEmail(cursorJogador.getString(4));
            jogador.setSenha(cursorJogador.getString(5));
            jogador.setCarisma(cursorJogador.getInt(6));
            jogador.setInteligencia(cursorJogador.getInt(7));
            jogador.setSorte(cursorJogador.getInt(8));
            jogador.setDinheiro(cursorJogador.getDouble(9));
            jogador.setCla(cursorJogador.getString(10));
        }

        cursorJogador.close();

        return jogador;

    }

    public boolean login_jogador(Context context, String email, String senha) {
        Cursor cursorJogador = getWritableDatabase().query(JOGADOR, new String[]{ID}, String.format("%s = '%s' AND %s = '%s'", EMAIL, email, SENHA, senha), null, null, null, null);

        if (cursorJogador.getCount() > 0) {
            cursorJogador.moveToFirst();
            salvarLoginShared(context, cursorJogador.getInt(0));
            cursorJogador.close();
            return true;
        } else {
            cursorJogador.close();
            return false;
        }

    }

    private void salvarLoginShared(Context context, int id) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putInt(USUARIO_LOGADO, id);
        editor.apply();
        editor.commit();
    }

}
