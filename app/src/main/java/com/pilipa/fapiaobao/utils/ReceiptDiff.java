package com.pilipa.fapiaobao.utils;

import android.support.v7.util.DiffUtil;

import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/21.
 */

public class ReceiptDiff extends DiffUtil.Callback {
    private ArrayList<Image> olddatas;
    private ArrayList<Image> newDatas;

    public ReceiptDiff(ArrayList<Image>  olddatas, ArrayList<Image>  newDatas) {
        this.olddatas = olddatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return olddatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return olddatas.get(oldItemPosition).equals(newDatas.get(newItemPosition));
    }
}
