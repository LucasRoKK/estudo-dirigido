package com.ljl.vidanatural.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ljl.vidanatural.R;
import com.ljl.vidanatural.databinding.ItemPicBinding;
import com.ljl.vidanatural.model.Pic;

import java.util.List;


public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private final List<Pic> mPics;
    private final PicListener mListener;

    public PicAdapter(List<Pic> pics, PicListener listener){
        mPics = pics;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPicBinding binding = ItemPicBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pic pic = mPics.get(position);
        holder.bind(pic);
    }

    @Override
    public int getItemCount() {
        return mPics.size();
    }

    public interface PicListener{
        void onLoadMore();
        void onMaisInfoClick(Pic pic);
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        private ItemPicBinding mBinding;

        public ViewHolder(ItemPicBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Pic pic) {

            mBinding.picBtnInfo.setOnClickListener(view -> {
                mListener.onMaisInfoClick(pic);
            });

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);

            Glide.with(mBinding.getRoot())
                    .load(pic.getFoto())
                    .apply(options)
                    .into(mBinding.picImgFoto);

            mBinding.setPic(pic);
            mBinding.executePendingBindings();
        }
    }
}
