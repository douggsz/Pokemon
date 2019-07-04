package com.ifsul.pokemon.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ifsul.pokemon.models.Pokedex;
import com.ifsul.pokemon.models.Pokemon;

import java.util.ArrayList;

import static com.ifsul.pokemon.Utils.constants.ID;
import static com.ifsul.pokemon.Utils.constants.ID_JOGADOR;
import static com.ifsul.pokemon.Utils.constants.ID_POKEMON;
import static com.ifsul.pokemon.Utils.constants.IMAGEM;
import static com.ifsul.pokemon.Utils.constants.NOME;
import static com.ifsul.pokemon.Utils.constants.POKEDEX;
import static com.ifsul.pokemon.Utils.constants.POKEMON;
import static com.ifsul.pokemon.Utils.constants.QTD_VISTO;
import static com.ifsul.pokemon.Utils.constants.TIPO;

public class DatabasePokemon extends SQLiteOpenHelper {

    private Pokedex pokedex;
    private Pokemon pokemon;
    private ArrayList<Pokedex> result;

    public DatabasePokemon(Context context, String nome, int versao) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Cria tabela Pokedex
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s INTEGER NOT NULL," +
                "%s INTEGER NOT NULL," +
                "%s INTEGER)", POKEDEX, ID, ID_POKEMON, ID_JOGADOR, QTD_VISTO));

        // Cria tabela Pokemon
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s VARCHAR(50) NOT NULL," +
                "%s INTEGER NOT NULL," +
                "%s VARCHAR(100))", POKEMON, ID, NOME, TIPO, IMAGEM));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Pokedex> listar_pokedex(int idJogador) {
        pokedex = new Pokedex();
        pokemon = new Pokemon();
        result = new ArrayList<Pokedex>();


        Cursor cursorPokedex = getWritableDatabase().query(POKEDEX,
                new String[]{ID, ID_POKEMON, ID_JOGADOR, QTD_VISTO}, String.format("%s = %s", ID_JOGADOR, idJogador), null, null, null, null);

        cursorPokedex.moveToFirst();
        while (cursorPokedex.moveToNext()) {

            Cursor cursorPokemon = getWritableDatabase().query(POKEMON,
                    new String[]{ID, NOME, TIPO, IMAGEM}, String.format("%s = %s", ID, cursorPokedex.getInt(1)), null, null, null, null);

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

            result.add(pokedex);

        }

        cursorPokedex.close();

        return result;
    }

    public void inserir_pokemon(String nome, int tipo, String imagem) {
        ContentValues values = new ContentValues();
        values.put(NOME, nome);
        values.put(TIPO, tipo);
        values.put(IMAGEM, imagem);
        getWritableDatabase().insert(POKEMON, null, values);
    }

    public void inserir_na_pokedex(int idJogador, int idPokemon, int qtdVisto) {
        ContentValues values = new ContentValues();
        values.put(ID_JOGADOR, idJogador);
        values.put(ID_POKEMON, idPokemon);
        values.put(QTD_VISTO, qtdVisto);
        getWritableDatabase().insert(POKEDEX, null, values);
    }

    public void remover_da_pokedex(int id) {
        String where = String.format("%s = %s", ID, id);
        getReadableDatabase().delete(POKEDEX, where, null);
        close();
    }


}
