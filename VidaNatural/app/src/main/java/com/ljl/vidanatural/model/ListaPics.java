
package com.ljl.vidanatural.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListaPics {

    @SerializedName("next")
    @Expose
    private int next;
    @SerializedName("array")
    @Expose
    private List<Pic> pic = null;

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public List<Pic> getPic() {
        return pic;
    }

    public void setPic(List<Pic> pic) {
        this.pic = pic;
    }

}
