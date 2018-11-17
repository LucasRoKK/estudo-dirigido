package com.ljl.vidanatural.model;

import com.google.gson.annotations.SerializedName;

public class Perfil {

    // SerializedName: nome que o campo vai receber no json
    @SerializedName("nome")
    private String mNome = "";

    @SerializedName("email")
    private String mEmail = "";

    @SerializedName("token")
    private String mToken = "";

    // Transient: o campo nao eh tranformado em json
    private transient String endereco = "";

    public String getNome() {
        return mNome;
    }

    public void setNome(final String nome) {
        mNome = nome;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(final String email) {
        mEmail = email;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(final String token) {
        mToken = token;
    }
}

