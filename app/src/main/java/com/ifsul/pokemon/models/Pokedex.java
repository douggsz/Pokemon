package com.ifsul.pokemon.models;

public class Pokedex {

    private int id;
    private Pokemon pokemon;
    private int idJogador;
    private int qtdVisto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public int getQtdVisto() {
        return qtdVisto;
    }

    public void setQtdVisto(int qtdVisto) {
        this.qtdVisto = qtdVisto;
    }
}
