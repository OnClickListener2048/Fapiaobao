package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

import static com.pilipa.fapiaobao.net.Constant.STATE_COMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_CONFIRMING;
import static com.pilipa.fapiaobao.net.Constant.STATE_INCOMPETENT;
import static com.pilipa.fapiaobao.net.Constant.STATE_MAILING;

/**
 * Created by edz on 2017/10/20.
 */

public class DemandsDetailsReceiptAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "UploadReceiptAdapter";

    private static final int TYPE_CAPTURE = 0x0001;
    private static final int TYPE_MEDIA = 0x0002;


    private ArrayList<Image> images;
    private final int imageResize;
    private RequestManager requestManager;
    private OnPhotoCapture onPhotoCapture;
    private OnImageClickListener onImageClickListener;
    private OnImageSelectListener onImageSelectListener;
    private Image image;

    public DemandsDetailsReceiptAdapter(ArrayList<Image> images, int imageResize) {
        this.images = images;
        this.imageResize = imageResize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        requestManager = Glide.with(parent.getContext());
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demands_details_receipt, parent, false);
            return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ImageViewHolder imageHolder = (ImageViewHolder) holder;
        image = images.get(position);
        String state = image.state;
        setStateVisibility(imageHolder,image);
        image.position = position;
        requestManager
                    .load(image.path)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .override(imageResize, imageResize*3/4)
                    .thumbnail(0.1f)
                    .into(imageHolder.iv_image_item);
            Log.d(TAG, "onBindViewHolder: requestManager.load");
            imageHolder.iv_image_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onImageClickListener != null) {
                        onImageClickListener.onImageClick(images, position);
                    }
                }
            });

    }
    private void setStateVisibility(ImageViewHolder imageHolder,Image image){
        FrameLayout.LayoutParams buttonParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            //相当于Button布局属性中的android:layout_gravity=right

        switch (image.state){
            case STATE_CONFIRMING:
                imageHolder.quality.setVisibility(View.GONE);
                imageHolder.wait_to_quality.setVisibility(View.GONE);
                imageHolder.wait_to_express.setVisibility(View.GONE);
                imageHolder.unquality.setVisibility(View.GONE);
                imageHolder.confirming.setVisibility(View.VISIBLE);
                buttonParams.gravity = Gravity.BOTTOM;
                break;
            case STATE_COMPETENT:
                imageHolder.quality.setVisibility(View.VISIBLE);
                imageHolder.wait_to_quality.setVisibility(View.GONE);
                imageHolder.wait_to_express.setVisibility(View.GONE);
                imageHolder.confirming.setVisibility(View.GONE);
                imageHolder.unquality.setVisibility(View.GONE);
                buttonParams.gravity = Gravity.TOP;
                break;
            case STATE_INCOMPETENT:
                imageHolder.unquality.setVisibility(View.VISIBLE);
                imageHolder.quality.setVisibility(View.GONE);
                imageHolder.wait_to_quality.setVisibility(View.GONE);
                imageHolder.wait_to_express.setVisibility(View.GONE);
                imageHolder.confirming.setVisibility(View.GONE);
                buttonParams.gravity = Gravity.TOP;
                break;
            case STATE_MAILING:
                imageHolder.quality.setVisibility(View.GONE);
                imageHolder.unquality.setVisibility(View.GONE);
                imageHolder.confirming.setVisibility(View.GONE);
                buttonParams.gravity = Gravity.BOTTOM;
                if(image.logisticsTradeno == null){
                    imageHolder.wait_to_quality.setVisibility(View.VISIBLE);
                    imageHolder.wait_to_express.setVisibility(View.GONE);
                }else{
                    imageHolder.wait_to_quality.setVisibility(View.GONE);
                    imageHolder.wait_to_express.setVisibility(View.VISIBLE);
                }
                break;
        }
        imageHolder.iv_image_item.setLayoutParams(buttonParams);
    }

    @Override
    public int getItemViewType(int position) {

            return TYPE_MEDIA;

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void refresh(ArrayList<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    public interface OnPhotoCapture {
        void capture();
    }

    public void setOnPhotoCapture(OnPhotoCapture onPhotoCapture) {
        this.onPhotoCapture = onPhotoCapture;
    }


    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }



    public interface OnImageClickListener {
        void onImageClick(ArrayList<Image> allItemList, int position);
    }

    public interface OnImageSelectListener {
        void onImageSelect(Image image);
    }

    public void setOnImageSelectListener(OnImageSelectListener onImageSelectListener) {
        this.onImageSelectListener = onImageSelectListener;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final TextView tv_isSelect;
        private final ImageView quality,unquality;
        private final ImageView wait_to_quality;
        private final ImageView wait_to_express;
        private final ImageView confirming;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv_image_item = (ImageView) itemView.findViewById(R.id.iv_image_item);
            tv_isSelect = (TextView) itemView.findViewById(R.id.rb);
            quality = (ImageView) itemView.findViewById(R.id.quality);
            unquality = (ImageView) itemView.findViewById(R.id.unquality);
            wait_to_quality = (ImageView) itemView.findViewById(R.id.wait_to_quality);
            wait_to_express = (ImageView) itemView.findViewById(R.id.wait_to_express);
            confirming = (ImageView) itemView.findViewById(R.id.confirming);
        }
    }
}
