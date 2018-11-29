package com.ljl.vidanatural.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ljl.vidanatural.activity.LogInActivity;
import com.ljl.vidanatural.activity.TermoDeUsoActivity;

public class MainActivity extends AppCompatActivity {

    //teste linux

    private static final String TAG = "App06";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        boolean estaLogado = VerificadorUtil.getLogado(this);
        Log.d(TAG, "onCreate: estaLogado = " + estaLogado);

        if (estaLogado) {
            Log.d(TAG, "onCreate: redireciona para a home");

            Intent intent = new Intent(this, TermoDeUsoActivity.class);
            intent.putExtra("oi", "Oi");
            intent.putExtra("numero", 999);

            startActivity(intent);
            finish();

        } else {
            Log.d(TAG, "onCreate: redireciona para a tela de login");

            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
