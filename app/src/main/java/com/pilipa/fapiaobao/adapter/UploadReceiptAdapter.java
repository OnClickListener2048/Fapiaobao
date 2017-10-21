package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/20.
 */

public class UploadReceiptAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "UploadReceiptAdapter";

    private static final int TYPE_CAPTURE = 0x0001;
    private static final int TYPE_MEDIA = 0x0002;


    private ArrayList<Image> images;
    private final int imageResize;
    private RequestManager requestManager;
    private OnPhotoCapture onPhotoCapture;
    private OnImageClickListener onImageClickListener;
    private OnImageSelectListener onImageSelectListener;

    public UploadReceiptAdapter(ArrayList<Image> images, int imageResize) {
        this.images = images;
        this.imageResize = imageResize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        requestManager = Glide.with(parent.getContext());
        View view;
        if (viewType == TYPE_CAPTURE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_myreceipt_capture, parent, false);
            return new CaptureViewHolder(view);
        } else if (viewType == TYPE_MEDIA) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_receipt_image, parent, false);
            return new ImageViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CaptureViewHolder) {
            CaptureViewHolder captureHolder = (CaptureViewHolder) holder;
            captureHolder.mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPhotoCapture != null) {
                        onPhotoCapture.capture();
                    }
                }
            });
        } else if (holder instanceof ImageViewHolder) {
            Log.d(TAG, "onBindViewHolder: (holder instanceof ImageViewHolder");
            ImageViewHolder imageHolder = (ImageViewHolder) holder;
            requestManager
                    .load(images.get(position).uri)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher)
                    .override(imageResize, imageResize)
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
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_CAPTURE;
        }
        if (position > 0) {
            return TYPE_MEDIA;
        }
        return super.getItemViewType(position);
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



    private static class CaptureViewHolder extends RecyclerView.ViewHolder {

        private TextView mHint;

        CaptureViewHolder(View itemView) {
            super(itemView);
            mHint = (TextView) itemView.findViewById(R.id.capture);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final TextView tv_isSelect;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv_image_item = (ImageView) itemView.findViewById(R.id.iv_image_item);
            tv_isSelect = (TextView) itemView.findViewById(R.id.rb);
        }
    }
}
