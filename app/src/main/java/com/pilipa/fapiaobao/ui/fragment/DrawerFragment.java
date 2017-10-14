package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.dialog.TimePickerDialog;
import com.pilipa.fapiaobao.ui.fragment.model.DrawerFrag;
import com.pilipa.fapiaobao.ui.widget.LabelsView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyt on 2017/10/14.
 */

public class DrawerFragment extends BaseFragment implements LabelsView.OnLabelClickListener, LabelsView.OnLabelSelectChangeListener {
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.show_more)
    TextView showMore;
    @Bind(R.id.labels)
    LabelsView labels;
    @Bind(R.id.start_data)
    LinearLayout startData;
    @Bind(R.id.final_date)
    LinearLayout finalDate;
    @Bind(R.id.start_need)
    EditText startNeed;
    @Bind(R.id.final_need)
    EditText finalNeed;
    @Bind(R.id.tv_startdate)
    TextView tvStartdate;
    @Bind(R.id.tv_finaldate)
    TextView tvFinaldate;
    private TexFragment texFragment;
    private TimePickerDialog timePickerDialog;


    private TimePickerDialog.TimePickerDialogInterface pickerDialogInterface_startdate = new TimePickerDialog.TimePickerDialogInterface() {
        @Override
        public void positiveListener() {
            tvStartdate.setText(timePickerDialog.getYear() + "-" + timePickerDialog.getMonth() + "-" + timePickerDialog.getDay());
        }

        @Override
        public void negativeListener() {

        }
    };

    private TimePickerDialog.TimePickerDialogInterface pickerDialogInterface_finaldate = new TimePickerDialog.TimePickerDialogInterface() {
        @Override
        public void positiveListener() {
            tvFinaldate.setText(timePickerDialog.getYear() + "-" + timePickerDialog.getMonth() + "-" + timePickerDialog.getDay());
        }

        @Override
        public void negativeListener() {

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dl;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        texFragment = (TexFragment) getParentFragment();
        timePickerDialog = new TimePickerDialog(getContext());
        labels.setSelectType(LabelsView.SelectType.MULTI);
        labels.setOnLabelClickListener(this);
        labels.setOnLabelSelectChangeListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

        labels.setLabels(DrawerFrag.initData(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.back, R.id.et_search, R.id.tv_search, R.id.show_more, R.id.labels, R.id.start_data, R.id.final_date, R.id.start_need, R.id.final_need})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                texFragment.closeDrawer();

                break;
            case R.id.et_search:
                break;
            case R.id.tv_search:
                BaseApplication.showToast("closedrawer");
                BaseApplication.showToast(labels.getLabels().toString());
                texFragment.closeDrawer();
                break;
            case R.id.show_more:
                break;
            case R.id.labels:
                break;
            case R.id.start_data:
                timePickerDialog.showDatePickerDialog(pickerDialogInterface_startdate);
                break;
            case R.id.final_date:
                timePickerDialog.showDatePickerDialog(pickerDialogInterface_finaldate);
                break;
            case R.id.start_need:
                break;
            case R.id.final_need:
                break;
        }
    }

    @Override
    public void onLabelClick(View label, String labelText, int position) {
        BaseApplication.showToast(labelText);
    }

    @Override
    public void onLabelSelectChange(View label, String labelText, boolean isSelect, int position) {
        BaseApplication.showToast(labelText);
    }
}
