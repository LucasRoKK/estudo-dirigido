package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.DistritoResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface DistritoServices {
    @Multipart
    @POST("get_distritos.php")
    Call<DistritoResponse> getDistritos(@PartMap Map<String, RequestBody> params);
}
