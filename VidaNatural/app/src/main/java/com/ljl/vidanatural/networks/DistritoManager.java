package com.ljl.vidanatural.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DistritoManager {
    private static final String URL = "http://services.koruthos.com.br/distrito/";
    private static DistritoManager sInstance = new DistritoManager();

    private final Retrofit mRetrofit;
    private final DistritoServices mService;

    private DistritoManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(DistritoServices.class);
    }

    public static DistritoManager getInstance() {
        if (sInstance == null) sInstance = new DistritoManager();
        return sInstance;
    }

    public static DistritoServices service() {
        return getInstance().mService;
    }

}
