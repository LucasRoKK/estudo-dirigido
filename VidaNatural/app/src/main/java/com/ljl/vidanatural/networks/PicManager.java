package com.ljl.vidanatural.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicManager {
    private static final String URL = "http://services.koruthos.com.br/api/";
    private static PicManager sInstance = new PicManager();

    private final Retrofit mRetrofit;
    private final PicServices mService;

    private PicManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(PicServices.class);
    }

    public static PicManager getInstance() {
        if (sInstance == null) sInstance = new PicManager();
        return sInstance;
    }

    public static PicServices service() {
        return getInstance().mService;
    }

}