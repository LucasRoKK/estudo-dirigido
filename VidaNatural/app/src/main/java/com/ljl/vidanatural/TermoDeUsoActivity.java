package com.ljl.vidanatural;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TermoDeUsoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_de_uso);


    }

    public void proximaTela(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


