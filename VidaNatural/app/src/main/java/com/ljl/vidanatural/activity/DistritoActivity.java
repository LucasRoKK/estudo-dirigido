package com.ljl.vidanatural.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.adapters.DistritoAdapter;
import com.ljl.vidanatural.model.Distrito;
import com.ljl.vidanatural.model.DistritoResponse;
import com.ljl.vidanatural.model.ListaDistrito;
import com.ljl.vidanatural.networks.DistritoManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DistritoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Distrito> mDistrito = new ArrayList<>();
    private DistritoAdapter mDistritoAdapter;
    private int mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distrito);

        final ProgressBar progressBar = findViewById(R.id.dist_progressbar);

        mDistrito = new ArrayList<>();

        mRecyclerView = findViewById(R.id.dist_recyclerview);

        mDistritoAdapter = new DistritoAdapter(mDistrito, this::proximaTela);

        carregarDistritos(0);

        progressBar.setVisibility(View.VISIBLE);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastCompletelyVisibleItemPosition() == mDistrito.size() -1){
                    onLoadMore();
                }
            }
        });
        progressBar.setVisibility(View.GONE);

        mRecyclerView.setAdapter(mDistritoAdapter);

    }

    public void carregarDistritos(int next){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(next));
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("next", body);

        DistritoManager.service().getDistritos(requestBodyMap).enqueue(new Callback<DistritoResponse>() {
            @Override
            public void onResponse(Call<DistritoResponse> call, Response<DistritoResponse> response) {
                if(response.body().getStatus() == 0 ){
                    onWebServiceResponse(response.body().getListaDistrito());
                }else{
                    onWebServiceError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<DistritoResponse> call, Throwable t) {
                onWebServiceError(t.getMessage());
            }
        });

    }


    private void onWebServiceResponse(ListaDistrito listaDistrito) {
        mNext = listaDistrito.getNext();
        mDistrito.addAll(listaDistrito.getDistrito());
        mDistritoAdapter.notifyDataSetChanged();
    }

    private void onWebServiceError(String message) {
        Toast.makeText(this, "Erro Web Service!", Toast.LENGTH_SHORT).show();
    }

    public void onLoadMore() {
        if(mNext > 0){
            carregarDistritos(mNext);
        }else{
            Toast.makeText(this, "Fim da lista!", Toast.LENGTH_SHORT).show();
        }
    }


    public void proximaTela(Distrito distrito){
        salvaDadosDist(distrito);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void salvaDadosDist(Distrito distrito) {
        SharedPreferences preferences = Objects.requireNonNull
                (this.getSharedPreferences("dist", Context.MODE_PRIVATE));
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome", distrito.getNome());
        editor.putString("id", distrito.getId());
        editor.apply();
    }

}

