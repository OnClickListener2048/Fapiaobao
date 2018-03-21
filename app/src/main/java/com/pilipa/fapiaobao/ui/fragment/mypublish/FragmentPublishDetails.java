package com.pilipa.fapiaobao.ui.fragment.mypublish;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mylibrary.utils.TLog;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.constants.Constant;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment2;
import com.pilipa.fapiaobao.ui.fragment.DemandsDetailsReceiptFragment3;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_ELECTRON;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_GENERAL_PAPER;
import static com.pilipa.fapiaobao.net.Constant.VARIETY_SPECIAL_PAPER;

/**
 * @author edz
 * @date 2018/3/20
 */

public class FragmentPublishDetails extends BaseFragment {
    private static final String TAG = "FragmentPublishDetails";
    @Bind(R.id.tv_invoice_status)
    TextView mTvInvoiceStatus;
    @Bind(R.id.tv_invoice_pieces)
    TextView mTvInvoicePieces;
    @Bind(R.id.ic_arrow)
    ImageView mIcArrow;
    @Bind(R.id.tv_elec)
    TextView mTvElec;
    @Bind(R.id.tv_elec_piece)
    TextView mTvElecPiece;
    @Bind(R.id.fl_elec)
    FrameLayout mFlElec;
    @Bind(R.id.tv_normal)
    TextView mTvNormal;
    @Bind(R.id.tv_normal_piece)
    TextView mTvNormalPiece;
    @Bind(R.id.fl_normal)
    FrameLayout mFlNormal;
    @Bind(R.id.tv_special)
    TextView mTvSpecial;
    @Bind(R.id.tv_special_piece)
    TextView mTvSpecialPiece;
    @Bind(R.id.fl_special)
    FrameLayout mFlSpecial;
    @Bind(R.id.rl_elec)
    RelativeLayout mRlElec;
    @Bind(R.id.rl_normal)
    RelativeLayout mRlNormal;
    @Bind(R.id.rl_special)
    RelativeLayout mRlSpecial;
    @Bind(R.id.ll_container)
    LinearLayout mLlContainer;
    @Bind(R.id.ll_sup_container)
    LinearLayout mLlSupContainer;
    private DemandsDetailsReceiptFragment3 mInstance3;
    private DemandsDetailsReceiptFragment mInstance1;
    private DemandsDetailsReceiptFragment2 mInstance2;
    private boolean isOpen = true;
    private DemandsDetailsReceiptFragment3 mDemandsDetailsReceiptFragment3;
    private DemandsDetailsReceiptFragment mDemandsDetailsReceiptFragment;
    private DemandsDetailsReceiptFragment2 mDemandsDetailsReceiptFragment2;

    public static FragmentPublishDetails newInstance(Bundle bundle) {
        FragmentPublishDetails fragmentPublishDetails = new FragmentPublishDetails();
        fragmentPublishDetails.setArguments(bundle);
        return fragmentPublishDetails;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_publish_details;
    }

    public void update(Bundle bundle) {
        String name = bundle.getString(Constant.INVOICE_STATUS);
        String totalPieces = bundle.getString(Constant.TOTAL_PIECES);
        ArrayList<Image> imageArrayList = bundle.getParcelableArrayList(Constant.DEMANDS_DETAILS_IMAGES);

        mTvInvoicePieces.setText(getString(R.string.end_with_piece, totalPieces));
        mTvInvoiceStatus.setText(name);

        ArrayList<Image> normalImages = new ArrayList<>();
        ArrayList<Image> specialImages = new ArrayList<>();
        ArrayList<Image> elecImages = new ArrayList<>();


        if (imageArrayList != null) {
            for (Image image : imageArrayList) {
                if (TextUtils.equals(image.getVariety(), VARIETY_GENERAL_PAPER)) {
                    normalImages.add(image);
                }
                if (TextUtils.equals(image.getVariety(), VARIETY_SPECIAL_PAPER)) {
                    specialImages.add(image);
                }
                if (TextUtils.equals(image.getVariety(), VARIETY_GENERAL_ELECTRON)) {
                    elecImages.add(image);
                }
            }
        }

        if (elecImages.size() > 0) {
            Bundle bundle3 = new Bundle();
            bundle3.putParcelableArrayList(Constant.PAPER_ELEC_RECEIPT_DATA, elecImages);
            bundle3.putString("activity", TAG);
            TLog.d(TAG, "mInstance3 == null===" + String.valueOf(mInstance3 == null));
            if (mInstance3 == null) {
                mInstance3 = DemandsDetailsReceiptFragment3.newInstance(bundle3);
            }
            TLog.d(TAG, "mInstance3.isAdded()===" + mInstance3.isAdded());
            if (mInstance3.isAdded()) {
                mInstance3.update(bundle3);
            } else {
                addDrawerFragment(R.id.fl_elec, mInstance3);
            }

            mTvElecPiece.setText(getString(R.string.end_with_piece, String.valueOf(elecImages.size())));
            mRlElec.setVisibility(View.VISIBLE);
            mFlElec.setVisibility(View.VISIBLE);
        } else {
            mRlElec.setVisibility(View.GONE);
            mFlElec.setVisibility(View.GONE);
        }

        if (normalImages.size() > 0) {
            Bundle bundle1 = new Bundle();
            bundle1.putParcelableArrayList(Constant.PAPER_NORMAL_RECEIPT_DATA, normalImages);
            bundle1.putString("activity", TAG);
            TLog.d(TAG, "mInstance1 == null===" + String.valueOf(mInstance1 == null));
            if (mInstance1 == null) {
                mInstance1 = DemandsDetailsReceiptFragment.newInstance(bundle1);
            }
            TLog.d(TAG, "mInstance1.isAdded()===" + mInstance1.isAdded());
            if (mInstance1.isAdded()) {
                mInstance1.update(bundle1);
            } else {
                addDrawerFragment(R.id.fl_normal, mInstance1);
            }

            mTvNormalPiece.setText(getString(R.string.end_with_piece, String.valueOf(normalImages.size())));
            mRlNormal.setVisibility(View.VISIBLE);
            mFlNormal.setVisibility(View.VISIBLE);
        } else {
            mRlNormal.setVisibility(View.GONE);
            mFlNormal.setVisibility(View.GONE);
        }

        if (specialImages.size() > 0) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList(Constant.PAPER_SPECIAL_RECEIPT_DATA, specialImages);
            bundle2.putString("activity", TAG);
            TLog.d(TAG, "mInstance2 == null===" + String.valueOf(mInstance2 == null));
            if (mInstance2 == null) {
                mInstance2 = DemandsDetailsReceiptFragment2.newInstance(bundle2);
            }
            TLog.d(TAG, "mInstance2.isAdded()===" + mInstance2.isAdded());
            if (mInstance2.isAdded()) {
                mInstance2.update(bundle2);
            } else {
                addDrawerFragment(R.id.fl_special, mInstance2);
            }
            mTvSpecialPiece.setText(getString(R.string.end_with_piece, String.valueOf(specialImages.size())));
            mRlSpecial.setVisibility(View.VISIBLE);
            mFlSpecial.setVisibility(View.VISIBLE);
        } else {
            mRlSpecial.setVisibility(View.GONE);
            mFlSpecial.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);


        mLlContainer.setVisibility(isOpen ? View.VISIBLE : View.GONE);

        mIcArrow.setImageResource(isOpen ? R.mipmap.collapse : R.mipmap.expand);
    }



    @Override
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        String name = arguments.getString(Constant.INVOICE_STATUS);
        String totalPieces = arguments.getString(Constant.TOTAL_PIECES);
        ArrayList<Image> imageArrayList = arguments.getParcelableArrayList(Constant.DEMANDS_DETAILS_IMAGES);

        mTvInvoicePieces.setText(getString(R.string.end_with_piece, totalPieces));
        mTvInvoiceStatus.setText(name);

        ArrayList<Image> normalImages = new ArrayList<>();
        ArrayList<Image> specialImages = new ArrayList<>();
        ArrayList<Image> elecImages = new ArrayList<>();


        if (imageArrayList != null) {
            for (Image image : imageArrayList) {
                if (TextUtils.equals(image.getVariety(), VARIETY_GENERAL_PAPER)) {
                    normalImages.add(image);
                }
                if (TextUtils.equals(image.getVariety(), VARIETY_SPECIAL_PAPER)) {
                    specialImages.add(image);
                }
                if (TextUtils.equals(image.getVariety(), VARIETY_GENERAL_ELECTRON)) {
                    elecImages.add(image);
                }
            }
        }

        if (elecImages.size() > 0) {
            Bundle bundle3 = new Bundle();
            bundle3.putParcelableArrayList(Constant.PAPER_ELEC_RECEIPT_DATA, elecImages);
            if (mInstance3 == null) {
                mInstance3 = DemandsDetailsReceiptFragment3.newInstance(bundle3);
            }

            if (mInstance3.isAdded()) {
                mInstance3.update(bundle3);
            } else {
                addDrawerFragment(R.id.fl_elec, mInstance3);
                TLog.d(TAG, " addDrawerFragment(R.id.fl_elec, mDemandsDetailsReceiptFragment3);");
            }

            mTvElecPiece.setText(getString(R.string.end_with_piece, String.valueOf(elecImages.size())));
        } else {
            mRlElec.setVisibility(View.GONE);
        }

        if (normalImages.size() > 0) {
            Bundle bundle1 = new Bundle();
            bundle1.putParcelableArrayList(Constant.PAPER_NORMAL_RECEIPT_DATA, normalImages);
            if (mInstance1 == null) {
                mInstance1 = DemandsDetailsReceiptFragment.newInstance(bundle1);
            }
            if (mInstance1.isAdded()) {
                mInstance1.update(bundle1);
            } else {
                addDrawerFragment(R.id.fl_normal, mInstance1);
                TLog.d(TAG, " addDrawerFragment(R.id.fl_elec, mDemandsDetailsReceiptFragment);");
            }

            mTvNormalPiece.setText(getString(R.string.end_with_piece, String.valueOf(normalImages.size())));
        } else {
            mRlNormal.setVisibility(View.GONE);
        }

        if (specialImages.size() > 0) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelableArrayList(Constant.PAPER_SPECIAL_RECEIPT_DATA, specialImages);
            if (mInstance2 == null) {
                mInstance2 = DemandsDetailsReceiptFragment2.newInstance(bundle2);
            }

            if (mInstance2.isAdded()) {
                mInstance2.update(bundle2);
            } else {
                addDrawerFragment(R.id.fl_special, mInstance2);
                TLog.d(TAG, " addDrawerFragment(R.id.fl_elec, mDemandsDetailsReceiptFragment2);");
            }

            mTvSpecialPiece.setText(getString(R.string.end_with_piece, String.valueOf(specialImages.size())));
        } else {
            mRlSpecial.setVisibility(View.GONE);
        }
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

    @OnClick(R.id.ic_arrow)
    public void onViewClicked() {
        isOpen = !isOpen;

        mLlContainer.setVisibility(isOpen ? View.VISIBLE : View.GONE);
        if (isOpen) {
            mLlContainer.setVisibility(View.VISIBLE);
        } else {
            mLlContainer.setVisibility(View.GONE);
        }


        mIcArrow.setImageResource(isOpen ? R.mipmap.collapse : R.mipmap.expand);

    }
}
