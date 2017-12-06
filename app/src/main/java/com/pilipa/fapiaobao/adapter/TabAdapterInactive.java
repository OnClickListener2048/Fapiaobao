package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.AppOperator;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.net.bean.invoice.AllInvoiceType;
import com.pilipa.fapiaobao.net.bean.invoice.DefaultInvoiceBean;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by edz on 2017/10/29.
 */

public class TabAdapterInactive extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AllInvoiceMoreTypesRecyclerAdapter.OnItemClickListener,TabAdapterActive.ItemDeleteListener {
    String TAG = "AllInvoiceAdapter";
    AllInvoiceType allInvoiceType;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private AllInvoiceMoreTypesRecyclerAdapter adapter;

    public TabAdapterInactive(AllInvoiceType allInvoiceType) {
        this.allInvoiceType = allInvoiceType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_invoice, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder viewHolder = (Holder) holder;
            if (allInvoiceType != null) {
                AllInvoiceType.DataBean.InvoiceCategoryBean invoiceCategory = allInvoiceType.getData().get(position).getInvoiceCategory();
                final List<AllInvoiceType.DataBean.InvoiceTypeListBean> invoiceTypeList = allInvoiceType.getData().get(position).getInvoiceTypeList();

                if (invoiceTypeList != null) {
                    if (invoiceTypeList.size() > 0) {

                        if (invoiceCategory != null) {
                            viewHolder.tv_title.setText(invoiceCategory.getLabel());
                        }

                        viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
                        viewHolder.recyclerView.setNestedScrollingEnabled(false);
                        adapter = new AllInvoiceMoreTypesRecyclerAdapter(invoiceTypeList);
                        adapter.setOnItemClickListener(this);
                        viewHolder.recyclerView.setAdapter(adapter);

                    }
                }


            }
        }
    }


    @Override
    public void onItemClick(AllInvoiceType.DataBean.InvoiceTypeListBean dataBean) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(dataBean);
        }
    }

    @Override
    public void onItemDelete(final DefaultInvoiceBean.DataBean dataBean) {
        TLog.log("onItemDelete1"+dataBean.getName());
        Observable.fromIterable(allInvoiceType.getData())
                .flatMap(new Function<AllInvoiceType.DataBean, ObservableSource<AllInvoiceType.DataBean.InvoiceTypeListBean>>() {
                    @Override
                    public ObservableSource<AllInvoiceType.DataBean.InvoiceTypeListBean> apply(AllInvoiceType.DataBean dataBean) throws Exception {
                        return Observable.fromIterable(dataBean.getInvoiceTypeList());
                    }
                }).filter(new Predicate<AllInvoiceType.DataBean.InvoiceTypeListBean>() {
            @Override
            public boolean test(AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean) throws Exception {
                TLog.log("onItemDelete2"+invoiceTypeListBean.getName());
                return TextUtils.equals(dataBean.getId(),invoiceTypeListBean.getId());
            }
        }).subscribeOn(Schedulers.from(AppOperator.getExecutor()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AllInvoiceType.DataBean.InvoiceTypeListBean>() {
            @Override
            public void accept(AllInvoiceType.DataBean.InvoiceTypeListBean invoiceTypeListBean) throws Exception {
                TLog.log("invoiceTypeListBean.setSelected(false);"+invoiceTypeListBean.getName());
                invoiceTypeListBean.setSelected(false);
                notifyDataSetChanged();
            }
        });

    }


    public interface OnItemClickListener {
        void onItemClick(AllInvoiceType.DataBean.InvoiceTypeListBean dataBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @Override
    public int getItemCount() {
        if (allInvoiceType.getData() == null) {
            return 0;
        }
        return allInvoiceType.getData().size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private  TextView tv_title;
        private  RecyclerView recyclerView;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.invoice_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_invoice);
        }
    }

}
