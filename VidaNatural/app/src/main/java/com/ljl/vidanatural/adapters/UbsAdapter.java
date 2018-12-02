package com.ljl.vidanatural.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.databinding.ItemMapaBinding;
import com.ljl.vidanatural.model.Ubs;

import java.util.List;

public class UbsAdapter extends RecyclerView.Adapter<UbsAdapter.ViewHolder> {

    private final List<Ubs> mUbs;
    private final UbsListener mListener;

    public UbsAdapter(List<Ubs> ubs, UbsListener listener){
        mUbs = ubs;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMapaBinding binding = ItemMapaBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ubs ubs = mUbs.get(position);
        holder.bind(ubs);
    }

    @Override
    public int getItemCount() { return mUbs.size();}

    public interface UbsListener{

        void onUbsSelecionada(Ubs ubs);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMapaBinding mBinding;

        public ViewHolder(ItemMapaBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Ubs ubs) {

            mBinding.mapaCardview.setOnClickListener(view -> {
                mListener.onUbsSelecionada(ubs);
            });

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);

            Glide.with(mBinding.getRoot())
                    .load(ubs.getFoto())
                    .apply(options)
                    .into(mBinding.mapaImgFoto);

            mBinding.setUbs(ubs);
            mBinding.executePendingBindings();
        }
    }
}
