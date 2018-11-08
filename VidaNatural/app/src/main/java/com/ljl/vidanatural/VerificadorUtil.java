package com.ljl.vidanatural;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;

public class VerificadorUtil {

    private static final String ARQUIVO_PREFERENCES = "config_file";

    private static final String ESTA_LOGADO = "esta_logado";
    private static final String PERFIL = "perfil";

    private VerificadorUtil() {
        // vazio
    }

    public static void setLogado(Context context, boolean estaLogado) {
        // Para salvar uma informacao no shared preferences
        // Recebe como parametro: Nome do arquivo e modo de acesso
        SharedPreferences.Editor editor = context.getSharedPreferences(ARQUIVO_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putBoolean(ESTA_LOGADO, estaLogado);
        editor.apply();
    }

    public static boolean getLogado(Context context) {
        // Para recuperar um valor do shared preferences, só buscar pela chave
        SharedPreferences preferences = context.getSharedPreferences(ARQUIVO_PREFERENCES, Context.MODE_PRIVATE);

        // Segundo parametro eh valor default caso nao ache a chave
        return preferences.getBoolean(ESTA_LOGADO, false);
    }


    public static void setPerfil(Context context, Perfil perfil) {
        SharedPreferences.Editor editor = context.getSharedPreferences(ARQUIVO_PREFERENCES, Context.MODE_PRIVATE).edit();

        Gson gson = new Gson();
        String json = gson.toJson(perfil, Perfil.class);
        Log.d("App06", json);

        editor.putString(PERFIL, json);

        editor.apply();
    }

    public static Perfil getPerfil(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(ARQUIVO_PREFERENCES, Context.MODE_PRIVATE);

        String json = preferences.getString(PERFIL, "");

        // Garantindo que a aplicacao nao vai quebrar
        if (json.isEmpty()) {
            return new Perfil();
        }

        Gson gson = new Gson();
        return  gson.fromJson(json, Perfil.class);
    }






}

