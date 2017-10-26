package com.pilipa.fapiaobao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.entity.CardItem;
import com.pilipa.fapiaobao.ui.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private float mBaseElevation2;
    private Context mContext;

    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        mContext = context;
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.fragment_company_details, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        FlowLayout mFlowLayout = (FlowLayout) view.findViewById(R.id.flowLayout);
        LinearLayout ll_qr_code2 = (LinearLayout) view.findViewById(R.id.ll_qr_code2);
        ImageView img_qr_code1 = (ImageView) view.findViewById(R.id.img_qr_code1);
        String[] types ={"汽车票","火车票","办公用品票","过桥票","餐饮票","餐饮票","餐饮票"};
        addView(types,mFlowLayout);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        if(position != 2){
            ll_qr_code2.setVisibility(View.VISIBLE);
            img_qr_code1.setVisibility(View.INVISIBLE);
        }else{
            ll_qr_code2.setVisibility(View.INVISIBLE);
            img_qr_code1.setVisibility(View.VISIBLE);
        }
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
    }
    private void addView(String[] data, FlowLayout layout) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        for (int i = 0; i < data.length; i++) {
            TextView tv = (TextView) inflater.inflate(R.layout.item_type, layout, false);
            tv.setText(data[i]);
            layout.addView(tv);
        }
    }
}
