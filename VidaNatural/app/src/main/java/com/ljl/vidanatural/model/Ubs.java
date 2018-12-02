
package com.ljl.vidanatural.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubs {

    @SerializedName("id")
    @Expose
    private java.lang.Object id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("endereco")
    @Expose
    private String endereco;
    @SerializedName("cidade")
    @Expose
    private String cidade;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("foto")
    @Expose
    private java.lang.Object foto;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("distrito")
    @Expose
    private String distrito;
    @SerializedName("mapa_url")
    @Expose
    private String mapaUrl;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public java.lang.Object getId() {
        return id;
    }

    public void setId(java.lang.Object id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public java.lang.Object getFoto() {
        return foto;
    }

    public void setFoto(java.lang.Object foto) {
        this.foto = foto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMapaUrl() {
        return mapaUrl;
    }

    public void setMapaUrl(String mapaUrl) {
        this.mapaUrl = mapaUrl;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
