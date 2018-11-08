package com.ljl.vidanatural.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.util.MainActivity;

public class BairroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bairro);

        }


    public void proximaTela(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

