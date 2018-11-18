package com.ljl.vidanatural.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.util.VerificadorUtil;
import com.ljl.vidanatural.model.Perfil;

public class LogInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //Google
    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SING_IN_CODE = 777;

    //Facebook
    private LoginButton mLoginButtom;

    private FirebaseAuth mFirebaseAuth;

    private CallbackManager mCallbackManager;

    //Salva dados
    Perfil perfil = new Perfil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        inicializarComponente();
        inicializarFirebaseCallback();
        clickButton();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = findViewById(R.id.SignInButtom);
        signInButton.setOnClickListener(v -> {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(intent, SING_IN_CODE);
        });

        // Sucesso ao efetuar login
        VerificadorUtil.setLogado(this, true);
    }


    //FACEBOOK
    private void clickButton() {
        mLoginButtom.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseLogin(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                alert("Operacao cancelada");
            }

            @Override
            public void onError(FacebookException error) {
                alert("Erro do login com o Facebook");
            }
        });
    }

    private void firebaseLogin(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        perfil.setNome(mFirebaseAuth.getCurrentUser().getDisplayName());
                        perfil.setEmail(mFirebaseAuth.getCurrentUser().getEmail());
                        perfil.setToken(mFirebaseAuth.getCurrentUser().getUid());
                        Intent i = new Intent(LogInActivity.this, TermoDeUsoActivity.class);
                        startActivity(i);
                    }else{
                        alert("Erro de autenticação com o Firebase");
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void inicializarFirebaseCallback() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
    }


    private void inicializarComponente() {
        mLoginButtom = findViewById(R.id.log_btn_facebook);
        mLoginButtom.setReadPermissions("email", "public_profile");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SING_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            HandleSigInForResult(result);
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void HandleSigInForResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(this, R.string.not_log_in, Toast.LENGTH_SHORT).show();
        }

    }

    private void goMainScreen() {
        Intent intent = new Intent(this, TermoDeUsoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
