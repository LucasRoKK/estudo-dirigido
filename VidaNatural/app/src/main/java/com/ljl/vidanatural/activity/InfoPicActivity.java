package com.ljl.vidanatural.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ljl.vidanatural.R;

public class InfoPicActivity extends AppCompatActivity {

    private TextView txtNome, txtDesc;
    private ImageView imgFoto;
    String fotoURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pic);

        txtNome = findViewById(R.id.info_txt_nome);
        txtDesc = findViewById(R.id.info_txt_desc);
        imgFoto = findViewById(R.id.info_img_foto);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        txtNome.setText(preferences.getString("nome", null));
        txtDesc.setText(preferences.getString("desc", null));
        fotoURL = preferences.getString("foto", null);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this)
                .load(fotoURL)
                .apply(options)
                .into(imgFoto);
    }
}
