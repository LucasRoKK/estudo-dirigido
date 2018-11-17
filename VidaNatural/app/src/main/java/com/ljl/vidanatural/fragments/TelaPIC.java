package com.ljl.vidanatural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.InfoPicActivity;

public class TelaPIC extends Fragment {

    private Button btnInformacoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_pic, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnInformacoes = view.findViewById(R.id.pic_btn_info);

        btnInformacoes.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), InfoPicActivity.class);
            startActivity(intent);
        });
    }

}
