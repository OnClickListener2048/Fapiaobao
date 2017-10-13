package com.pilipa.fapiaobao.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TexFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TexFragment extends BaseFragment {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.message)
    ImageView message;
    @Bind(R.id.all)
    Button all;
    @Bind(R.id.collection)
    RelativeLayout collection;
    @Bind(R.id.general)
    TextView general;
    @Bind(R.id.ll_general)
    LinearLayout llGeneral;
    @Bind(R.id.bonus)
    TextView bonus;
    @Bind(R.id.ll_bonus)
    LinearLayout llBonus;
    @Bind(R.id.credit)
    TextView credit;
    @Bind(R.id.ll_credit)
    LinearLayout llCredit;
    @Bind(R.id.filter)
    TextView filter;
    @Bind(R.id.ll_filter)
    LinearLayout llFilter;
    @Bind(R.id.btn_collection)
    Button btn_collection;
    @Bind(R.id.errorview)
    FrameLayout fl_error;
    private FragmentCollection fragmentCollection;
    private FragmentAll fragmentAll;

    public TexFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TexFragment newInstance() {
        TexFragment fragment = new TexFragment();

        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tex;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        all.setSelected(true);
        btn_collection.setSelected(false);
        fl_error.setVisibility(View.GONE);
        fragmentAll = new FragmentAll();
        fragmentCollection = new FragmentCollection();
        addFragment(R.id.container, fragmentAll);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.title, R.id.scan, R.id.message, R.id.all, R.id.collection, R.id.general, R.id.ll_general, R.id.bonus, R.id.ll_bonus, R.id.credit, R.id.ll_credit, R.id.filter, R.id.ll_filter, R.id.btn_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title:
                BaseApplication.showToast("title");
                break;
            case R.id.scan:
                BaseApplication.showToast("scan");
                break;
            case R.id.message:
                BaseApplication.showToast("message");
                break;
            case R.id.all:
                BaseApplication.showToast("all");
                allonSelect();
                addFragment(R.id.container, fragmentAll);
                break;
            case R.id.collection:
            case R.id.btn_collection:
                BaseApplication.showToast("collection");
                collectionOnSelect();
                addFragment(R.id.container, fragmentCollection);
                break;
            case R.id.general:
            case R.id.ll_general:
                BaseApplication.showToast("general");
                break;
            case R.id.bonus:
            case R.id.ll_bonus:
                BaseApplication.showToast("bonus");
                break;
            case R.id.credit:
            case R.id.ll_credit:
                BaseApplication.showToast("credit");
                break;
            case R.id.filter:
            case R.id.ll_filter:
                BaseApplication.showToast("filter");
                break;
        }
    }

    private void collectionOnSelect() {
        if (btn_collection.isSelected()) {
            return;
        } else {
            btn_collection.setSelected(true);
            btn_collection.setTextColor(getResources().getColor(R.color.white));
            btn_collection.setBackgroundColor(getResources().getColor(R.color.btn_reception));
            all.setSelected(false);
            all.setTextColor(getResources().getColor(R.color.black));
            all.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private void allonSelect() {
        if (all.isSelected()) {
            return;
        } else {
            all.setSelected(true);
            all.setTextColor(getResources().getColor(R.color.white));
            all.setBackgroundColor(getResources().getColor(R.color.btn_reception));
            btn_collection.setSelected(false);
            btn_collection.setTextColor(getResources().getColor(R.color.black));
            btn_collection.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }




}
