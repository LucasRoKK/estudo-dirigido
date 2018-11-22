
package com.ljl.vidanatural.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PicResponse {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("object")
    @Expose
    private ListaPics listaPics;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ListaPics getListaPics() {
        return listaPics;
    }

    public void setListaPics(ListaPics listaPics) {
        this.listaPics = listaPics;
    }

}
