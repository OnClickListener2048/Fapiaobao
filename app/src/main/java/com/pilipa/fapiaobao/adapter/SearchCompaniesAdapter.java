package com.pilipa.fapiaobao.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.TestBean;

/**
 * Created by edz on 2018/1/3.
 */

public class SearchCompaniesAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {


    public SearchCompaniesAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.company_name, item.getCompanyName())
                .setText(R.id.tex_number, item.getTexNum());
    }
}
