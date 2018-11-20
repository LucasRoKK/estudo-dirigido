package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.Pic;
import retrofit2.Call;
import retrofit2.http.POST;

public interface PlaceHolderServices {
    @POST("pic")
    Call<Pic[]> listarPic();
}
