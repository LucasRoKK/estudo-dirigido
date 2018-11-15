package com.ljl.vidanatural.model;

public class Pic {

    private String mNome = "";
    private String mFoto = "";

    public Pic () {this("", "");}

    public Pic (final String nome, final String foto) {
        mNome = nome;
        mFoto = foto;
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(final String nome) {
        mNome = nome;
    }

    public String getFoto() {
        return mFoto;
    }

    public void setFoto(final String foto) {
        mFoto = foto;
    }

}
