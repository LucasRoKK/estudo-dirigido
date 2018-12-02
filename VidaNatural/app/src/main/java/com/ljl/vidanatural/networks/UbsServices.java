package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.UbsResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface UbsServices {
    @Multipart
    @POST("get_ubs_por_distrito.php")
    Call<UbsResponse> getUbs(@PartMap Map<String, RequestBody> params);
}
