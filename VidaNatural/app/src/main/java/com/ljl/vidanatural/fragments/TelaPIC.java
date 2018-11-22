package com.ljl.vidanatural.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.activity.InfoPicActivity;
import com.ljl.vidanatural.adapters.PicAdapter;
import com.ljl.vidanatural.model.ListaPics;
import com.ljl.vidanatural.model.Pic;
import com.ljl.vidanatural.model.PicResponse;
import com.ljl.vidanatural.networks.PicManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPIC extends Fragment implements PicAdapter.PicListener {

    private Button btnInformacoes;
    private TextView txtNome;
    private List<Pic> mPics;
    private int mNext;
    private PicAdapter mPicAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tela_pic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

        //criar adapter

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPics = new ArrayList<>();

        RecyclerView recyclerView = getActivity().findViewById(R.id.pic_recyclerview);

        mPicAdapter = new PicAdapter(mPics, this);


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
        mPics.addAll(listaPics.getPic());
        mNext = listaPics.getNext();
        //resposta para o adapter para avisar que tem mais dados
        mPicAdapter.notifyDataSetChanged();

    }

    private void onWebServiceError(String mensagem) {

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
        Toast.makeText(getActivity(), "Chama Activity InfoPicActivity" + pic.getNome(), Toast.LENGTH_SHORT).show();
    }
}
