package com.ljl.vidanatural.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljl.vidanatural.R;

public class InfoPicActivity extends AppCompatActivity {

    private TextView txtNome, txtDesc;
    private ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pic);

        txtNome = findViewById(R.id.info_txt_nome);
        txtDesc = findViewById(R.id.info_txt_desc);
        imgFoto = findViewById(R.id.info_img_foto);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        txtNome.setText(preferences.getString("nome", "não encontrado"));
        txtDesc.setText(preferences.getString("desc", "não encontrado"));
    }
}
