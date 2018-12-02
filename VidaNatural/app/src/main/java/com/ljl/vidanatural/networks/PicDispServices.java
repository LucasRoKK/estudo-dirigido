package com.ljl.vidanatural.networks;

import com.ljl.vidanatural.model.PicResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface PicDispServices {
    @Multipart
    @POST("get_pics_por_ubs.php")
    Call<PicResponse> getPicsDisp(@PartMap Map<String, RequestBody> params);
}
