package com.ljl.vidanatural.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ljl.vidanatural.R;
import com.ljl.vidanatural.adapters.PicDispAdapter;
import com.ljl.vidanatural.model.ListaPics;
import com.ljl.vidanatural.model.Pic;
import com.ljl.vidanatural.model.PicResponse;
import com.ljl.vidanatural.networks.PicDispManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PicsDisponiveisActivity extends AppCompatActivity implements PicDispAdapter.PicDispListener {

    private RecyclerView mRecyclerView;
    private List<Pic> mPics = new ArrayList<>();
    private PicDispAdapter mPicDispAdapter;
    private int mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pics_disponiveis);

        mPics = new ArrayList<>();

        mRecyclerView = findViewById(R.id.picdisp_recyclerview);

        mPicDispAdapter = new PicDispAdapter(mPics, this);

        carregarPics(0, 5);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(layoutManager.findLastCompletelyVisibleItemPosition() == mPics.size() -1){
                    onLoadMore();
                }
            }
        });

        mRecyclerView.setAdapter(mPicDispAdapter);
    }

    public void carregarPics(int next, int id){
        RequestBody bodyNext = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(next));
        RequestBody bodyId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("next", bodyNext);
        requestBodyMap.put("ubs_id", bodyId);

        PicDispManager.service().getPicsDisp(requestBodyMap).enqueue(new Callback<PicResponse>() {
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
        mPicDispAdapter.notifyDataSetChanged();
    }

    private void onWebServiceError(String message) {
        Toast.makeText(this, "Erro Web Service!", Toast.LENGTH_SHORT).show();
    }

    public void onLoadMore() {
        if(mNext > 0){
            carregarPics(mNext, 5);
        }else{
            Toast.makeText(this, "Fim da lista!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
