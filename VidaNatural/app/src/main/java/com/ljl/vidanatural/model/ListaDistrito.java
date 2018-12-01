
package com.ljl.vidanatural.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaDistrito {

    @SerializedName("next")
    @Expose
    private Integer next;
    @SerializedName("array")
    @Expose
    private List<Distrito> distrito = null;

    public int getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<Distrito> getDistrito() {
        return distrito;
    }

    public void setDistrito(List<Distrito> distrito) {
        this.distrito = distrito;
    }

}
