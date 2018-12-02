package com.ljl.vidanatural.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UbsManager {
    private static final String URL = "http://services.koruthos.com.br/ubs/";
    private static UbsManager sInstance = new UbsManager();

    private final Retrofit mRetrofit;
    private final UbsServices mService;

    private UbsManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(UbsServices.class);
    }

    public static UbsManager getInstance() {
        if (sInstance == null) sInstance = new UbsManager();
        return sInstance;
    }

    public static UbsServices service() {
        return getInstance().mService;
    }
}
