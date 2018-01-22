package com.pilipa.fapiaobao.adapter.publish;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.me.FavoriteCompanyBean;

import java.util.List;

/**
 * Created by edz on 2018/1/2.
 */

public class FavoriteCompaniesSelectorAdapter extends BaseQuickAdapter<FavoriteCompanyBean.DataBean, BaseViewHolder> {
    public FavoriteCompaniesSelectorAdapter(@LayoutRes int layoutResId, @Nullable List<FavoriteCompanyBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FavoriteCompanyBean.DataBean item) {
        helper.setText(R.id.item_simple_text, item.getName());
    }

}
