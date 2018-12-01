
package com.ljl.vidanatural.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distrito {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nome")
    @Expose
    private String nome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
