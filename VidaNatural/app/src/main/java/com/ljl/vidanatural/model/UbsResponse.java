
package com.ljl.vidanatural.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UbsResponse {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("object")
    @Expose
    private ListaUbs listaUbs;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListaUbs getListaUbs() {
        return listaUbs;
    }

    public void setListaUbs(ListaUbs listaUbs) {
        this.listaUbs = listaUbs;
    }

}
