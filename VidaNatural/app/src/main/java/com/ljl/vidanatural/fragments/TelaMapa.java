package com.ljl.vidanatural.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.LogInActivity;
import com.ljl.vidanatural.adapters.UbsAdapter;
import com.ljl.vidanatural.model.ListaUbs;
import com.ljl.vidanatural.model.Ubs;
import com.ljl.vidanatural.model.UbsResponse;
import com.ljl.vidanatural.networks.UbsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TelaMapa extends Fragment implements UbsAdapter.UbsListener  {

    private List<Ubs> mUbs;
    private int mNext;
    private String mId;
    private UbsAdapter mUbsAdapter;
    private RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_mapa, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mUbs = new ArrayList<>();

        mRecyclerView = getActivity().findViewById(R.id.mapa_recyclerview);

        mUbsAdapter = new UbsAdapter(mUbs, this);

        mRecyclerView.setAdapter(mUbsAdapter);

        SharedPreferences preferences = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);

        mId = preferences.getString("id", String.valueOf(Context.MODE_PRIVATE));

        carregarUbs(0, "1");

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastCompletelyVisibleItemPosition() == mUbs.size() -1){
                    onLoadMore();
                }
            }
        });
    }

    public void carregarUbs(int next, String id){

        RequestBody bodyNext = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(next));
        RequestBody bodyId = RequestBody.create(MediaType.parse("text/plain"), id);
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("next", bodyNext);
        requestBodyMap.put("distrito_id", bodyId);

        UbsManager.service().getUbs(requestBodyMap).enqueue(new Callback<UbsResponse>() {
            @Override
            public void onResponse(Call<UbsResponse> call, Response<UbsResponse> response) {
                if(response.body().getStatus() == 0 ){
                    onWebServiceResponse(response.body().getListaUbs());
                }else{
                    onWebServiceError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UbsResponse> call, Throwable t) {
                onWebServiceError(t.getMessage());
            }
        });
    }

    private void onWebServiceResponse(ListaUbs listaUbs) {
        mNext = listaUbs.getNext();
        mUbs.addAll(listaUbs.getUbs());
        mUbsAdapter.notifyDataSetChanged();
    }

    private void onWebServiceError(String mensagem) {
        Toast.makeText(getActivity(), "Erro Web Service!", Toast.LENGTH_SHORT).show();
    }

    public void onLoadMore() {
        if(mNext > 0){
            carregarUbs(mNext, mId);
        }else{
            Toast.makeText(getActivity(), "Fim da lista!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onUbsSelecionada(final Ubs ubs){
        Intent intent = new Intent(getActivity(), LogInActivity.class);
        startActivity(intent);
    }
}
