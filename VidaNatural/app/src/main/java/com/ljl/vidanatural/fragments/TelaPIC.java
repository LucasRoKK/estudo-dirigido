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
import com.ljl.vidanatural.activity.InfoPicActivity;
import com.ljl.vidanatural.adapters.PicAdapter;
import com.ljl.vidanatural.model.ListaPics;
import com.ljl.vidanatural.model.Pic;
import com.ljl.vidanatural.model.PicResponse;
import com.ljl.vidanatural.networks.PicManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPIC extends Fragment implements PicAdapter.PicListener {

    private List<Pic> mPics;
    private int mNext;
    private PicAdapter mPicAdapter;
    private RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tela_pic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPics = new ArrayList<>();

        mRecyclerView = getActivity().findViewById(R.id.pic_recyclerview);

        mPicAdapter = new PicAdapter(mPics, this);

        mRecyclerView.setAdapter(mPicAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        carregarPics(0);
    }

    public void carregarPics(int next){
        PicManager.service().listarPics(next).enqueue(new Callback<PicResponse>() {
            @Override
            public void onResponse(Call<PicResponse> call, Response<PicResponse> response) {

                if(response.body().getStatus() == 0 ){
                    onWebServiceResponse(response.body().getListaPics());
                }else{
                    onWebServiceError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<PicResponse> call, Throwable t) {
                onWebServiceError(t.getMessage());
            }
        });

    }

    private void onWebServiceResponse(ListaPics listaPics) {
        mNext = listaPics.getNext();
        mPics.addAll(listaPics.getPic());
        mPicAdapter.notifyDataSetChanged();
    }

    private void onWebServiceError(String mensagem) {
        Toast.makeText(getActivity(), "Erro Web Service!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if(mNext > 0){
            carregarPics(mNext);
        }else{
            Toast.makeText(getActivity(), "Fim da lista!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMaisInfoClick(Pic pic) {
        salvaDadosPic(pic);
        Intent intent = new Intent(getActivity(), InfoPicActivity.class);
        startActivity(intent);
    }

    public void salvaDadosPic(Pic pic){
        SharedPreferences preferences = Objects.requireNonNull
                (this.getActivity()).getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome", pic.getNome());
        editor.putString("desc", pic.getDescricao());
        editor.putString("foto", pic.getFoto());
        editor.apply();
    }
}
