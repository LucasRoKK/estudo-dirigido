package com.ljl.vidanatural.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicDispManager {
    private static final String URL = "http://services.koruthos.com.br/pic/";
    private static PicDispManager sInstance = new PicDispManager();

    private final Retrofit mRetrofit;
    private final PicDispServices mService;

    private PicDispManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(PicDispServices.class);
    }

    public static PicDispManager getInstance() {
        if (sInstance == null) sInstance = new PicDispManager();
        return sInstance;
    }

    public static PicDispServices service() {
        return getInstance().mService;
    }
}
