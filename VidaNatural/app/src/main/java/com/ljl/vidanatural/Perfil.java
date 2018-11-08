package com.ljl.vidanatural;

import com.google.gson.annotations.SerializedName;

public class Perfil {

    // SerializedName: nome que o campo vai receber no json
    @SerializedName("nome")
    private String mNome = "";

    @SerializedName("email")
    private String mEmail = "";

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
}

