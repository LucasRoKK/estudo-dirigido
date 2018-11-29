package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.PicResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface PicServices {
    @Multipart
    @POST("pic/read.php")
    Call<PicResponse> getPics(@PartMap Map<String, RequestBody> params);
}
