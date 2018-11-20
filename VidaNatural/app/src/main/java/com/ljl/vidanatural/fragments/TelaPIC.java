package com.ljl.vidanatural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.InfoPicActivity;
import com.ljl.vidanatural.model.Pic;
import com.ljl.vidanatural.networks.PicManager;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPIC extends Fragment {

    private Button btnInformacoes;
    private TextView txtNome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_pic, container, false);

        PicManager.service().listarPic().enqueue(new Callback<Pic[]>() {
            @Override
            public void onResponse(final Call<Pic[]> call, final Response<Pic[]> response) {
                Toast.makeText(TelaPIC.this, "Resposta recebida!", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onResponse: " + Arrays.toString(response.body()));
                onPicResponse(response.body());
            }

            @Override
            public void onFailure(final Call<Pic[]> call, final Throwable t) {
                Toast.makeText(TelaPIC.this, "Erro ao receber resposta!", Toast.LENGTH_SHORT).show();
                exibeMensagemErro(t.getMessage());
            }
        });

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnInformacoes = view.findViewById(R.id.pic_btn_info);
        txtNome = view.findViewById(R.id.pic_txt_nome);

        btnInformacoes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoPicActivity.class);
            startActivity(intent);
        });
    }

    private void onPicResponse(final Pic[] body) {
        String nome = "";

        for (Pic pic : body) {
            nome += pic.getNome();
        }

        txtNome.setText(nome);

    }

    private void exibeMensagemErro(final String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
