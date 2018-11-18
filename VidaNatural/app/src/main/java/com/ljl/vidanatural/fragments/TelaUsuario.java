package com.ljl.vidanatural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.LogInActivity;
import com.ljl.vidanatural.model.Perfil;

public class TelaUsuario extends Fragment {

    private Button btnLogout;
    private TextView txtNome, txtEmail;
    private ImageView imgUser;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mFirebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_usuario, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnLogout = view.findViewById(R.id.user_btn_logout);
        txtNome = view.findViewById(R.id.user_txt_nome);
        txtEmail = view.findViewById(R.id.user_txt_email);
        imgUser = view.findViewById(R.id.user_img_foto);

        inicializarFirebase();
        LogOut();
    }


    private void inicializarFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = firebaseAuth -> {
            mFirebaseUser = firebaseAuth.getCurrentUser();
            if (mFirebaseUser != null){
                exibirDados(mFirebaseUser);
            } else {
                LogOut();
            }
        };
    }

    private void exibirDados(FirebaseUser mFirebaseUser) {
        Glide.with(TelaUsuario.this).load(mFirebaseUser.getPhotoUrl()).into(imgUser);
        txtNome.setText(mFirebaseUser.getDisplayName());
        txtEmail.setText(mFirebaseUser.getEmail());
    }


    private void LogOut() {

            btnLogout.setOnClickListener(v -> {
                mFirebaseAuth.signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
            });
    }

    @Override
    public void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
