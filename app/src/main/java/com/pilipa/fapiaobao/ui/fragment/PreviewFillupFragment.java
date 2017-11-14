package com.pilipa.fapiaobao.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseFragment;
import com.pilipa.fapiaobao.ui.model.Image;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by edz on 2017/10/20.
 */

public class PreviewFillupFragment extends BaseFragment {
    private static final String TAG = "PreviewImageFragment";
    private static final String FRAG_IMAGE = "fragment_image";
    @Bind(R.id.image_view)
    ImageViewTouch imageView;
    @Bind(R.id.rb_preview_viewpager)
    RadioButton rbPreviewViewpager;
    @Bind(R.id.tv_preview_viewpager_delete)
    TextView tvPreviewViewpagerDelete;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview_fillup;
    }

    public static PreviewFillupFragment newInstance(Image image) {
        PreviewFillupFragment previewImageFragment = new PreviewFillupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAG_IMAGE, image);
        previewImageFragment.setArguments(bundle);
        return previewImageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Image imageItem = getArguments().getParcelable(FRAG_IMAGE);


        ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image_view);
        image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        Log.d(TAG, "onViewCreated: imageItem.isFromNet ?---"
                + imageItem.isFromNet + "imageItem.path----" +
                imageItem.path + "imageItem.uri)-----" + imageItem.uri);
        Glide.with(getActivity())
                .load(imageItem.isFromNet ? imageItem.path : imageItem.uri)
                .asBitmap()
                .error(R.mipmap.error_big)
                .placeholder(R.mipmap.loading_big)
                .into(image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_view, R.id.rb_preview_viewpager, R.id.tv_preview_viewpager_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_view:
                break;
            case R.id.rb_preview_viewpager:
                break;
            case R.id.tv_preview_viewpager_delete:
                break;
        }
    }

    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).resetMatrix();
        }
    }
}
