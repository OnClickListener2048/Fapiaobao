package com.example.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lyt on 2017/10/9.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {


    @Override
    public NormalAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NormalAdapter.VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class VH extends RecyclerView.ViewHolder{
        public VH(View v) {
            super(v);
        }
    }
}
