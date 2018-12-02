package com.ljl.vidanatural.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.databinding.ItemDistritoBinding;
import com.ljl.vidanatural.databinding.ItemPicBinding;
import com.ljl.vidanatural.model.Distrito;
import com.ljl.vidanatural.model.Pic;

import java.util.List;

public class DistritoAdapter extends RecyclerView.Adapter<DistritoAdapter.ViewHolder> {

    private final List<Distrito> mDistrito;
    private final DistritoListener mListener;

    public DistritoAdapter(@NonNull List<Distrito> distritos, @NonNull DistritoListener listener){
        mDistrito = distritos;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDistritoBinding binding = ItemDistritoBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Distrito distrito = mDistrito.get(position);
        holder.bind(distrito);
    }

    @Override
    public int getItemCount() {
        return mDistrito.size();
    }

    public interface DistritoListener{
        void onSelDistrito(Distrito distrito);

    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        private ItemDistritoBinding mBinding;

        public ViewHolder(ItemDistritoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Distrito distrito) {

            mBinding.ditrBtnSel.setOnClickListener(view -> {
                mListener.onSelDistrito(distrito);
            });

            mBinding.setDistrito(distrito);
            mBinding.executePendingBindings();
        }
    }
}
