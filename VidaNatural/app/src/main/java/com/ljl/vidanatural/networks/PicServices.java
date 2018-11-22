package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.PicResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PicServices {
    @POST("pic/read.php")
    Call<PicResponse> listarPics(@Body int next);
}
