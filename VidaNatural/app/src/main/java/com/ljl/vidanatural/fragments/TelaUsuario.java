package com.ljl.vidanatural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.LogInActivity;

public class TelaUsuario extends Fragment {

    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_usuario, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnLogout = view.findViewById(R.id.user_btn_logout);

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LogInActivity.class);
            startActivity(intent);
        });
    }

}
