
package com.ljl.vidanatural.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaUbs {

    @SerializedName("next")
    @Expose
    private Integer next;
    @SerializedName("array")
    @Expose
    private List<Ubs> ubs = null;

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<Ubs> getUbs() {
        return ubs;
    }

    public void setUbs(List<Ubs> ubs) {
        this.ubs = ubs;
    }

}
