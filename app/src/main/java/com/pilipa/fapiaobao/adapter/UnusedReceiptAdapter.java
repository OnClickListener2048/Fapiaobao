package com.pilipa.fapiaobao.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;

/**
 * Created by edz on 2017/10/27.
 */

public class UnusedReceiptAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "UnusedReceiptAdapter";

    private static final int TYPE_CAPTURE = 0x0001;
    private static final int TYPE_MEDIA = 0x0002;


    private ArrayList<Image> images;
    private final int imageResize;
    private RequestManager requestManager;
    private UnusedReceiptAdapter.OnPhotoCapture onPhotoCapture;
    private UnusedReceiptAdapter.OnImageClickListener onImageClickListener;
    private UnusedReceiptAdapter.OnImageSelectListener onImageSelectListener;
    private UnusedReceiptAdapter.OnImageLongClickListener onImageLongClickListener;

    public UnusedReceiptAdapter(ArrayList<Image> images, int imageResize) {
        this.images = images;
        this.imageResize = imageResize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        requestManager = Glide.with(parent.getContext());
        View view;
        if (viewType == TYPE_CAPTURE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_myreceipt_capture, parent, false);
            return new UnusedReceiptAdapter.CaptureViewHolder(view);
        } else if (viewType == TYPE_MEDIA) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unused_receipt, parent, false);
            return new UnusedReceiptAdapter.ImageViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof UnusedReceiptAdapter.CaptureViewHolder) {
            UnusedReceiptAdapter.CaptureViewHolder captureHolder = (UnusedReceiptAdapter.CaptureViewHolder) holder;
            captureHolder.mHint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPhotoCapture != null) {
                        onPhotoCapture.capture();
                    }
                }
            });
        } else if (holder instanceof UnusedReceiptAdapter.ImageViewHolder) {
            Log.d(TAG, "onBindViewHolder: (Financeholder instanceof ImageViewHolder");
            final UnusedReceiptAdapter.ImageViewHolder imageHolder = (UnusedReceiptAdapter.ImageViewHolder) holder;
            final Image image = images.get(position);
            image.position = position;
            imageHolder.cb_isSelect.setChecked(image.isSelected);
            imageHolder.cb_isSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    imageHolder.cb_isSelect.setChecked(isChecked);
                    image.isSelected = isChecked;
                    if (onImageSelectListener != null) {
                        onImageSelectListener.onImageSelect(image);
                    }
                }
            });
            requestManager
                    .load(image.isFromNet?image.path:image.uri)
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
            imageHolder.iv_image_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onImageLongClickListener != null) {
                        onImageLongClickListener.onImageLongClick(imageHolder.iv_image_item,image,position);
                    }
                    return true;
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
    public void delete(int pos) {
        images.remove(pos);
        notifyDataSetChanged();
    }

    public interface OnPhotoCapture {
        void capture();
    }

    public void setOnPhotoCapture(UnusedReceiptAdapter.OnPhotoCapture onPhotoCapture) {
        this.onPhotoCapture = onPhotoCapture;
    }


    public void setOnImageClickListener(UnusedReceiptAdapter.OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener {
        void onImageClick(ArrayList<Image> allItemList, int position);
    }

    public interface OnImageSelectListener {
        void onImageSelect(Image image);
    }

    public void setOnImageSelectListener(UnusedReceiptAdapter.OnImageSelectListener onImageSelectListener) {
        this.onImageSelectListener = onImageSelectListener;
    }

    public interface OnImageLongClickListener {
        void onImageLongClick(View view ,Image image,int pos);
    }

    public void setOnImageLongClickListener(UnusedReceiptAdapter.OnImageLongClickListener onImageLongClickListener) {
        this.onImageLongClickListener = onImageLongClickListener;
    }



    private static class CaptureViewHolder extends RecyclerView.ViewHolder {

        private ImageView mHint;

        CaptureViewHolder(View itemView) {
            super(itemView);
            mHint = (ImageView) itemView.findViewById(R.id.capture);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final CheckBox cb_isSelect;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv_image_item = (ImageView) itemView.findViewById(R.id.iv_image_item);
            cb_isSelect = (CheckBox) itemView.findViewById(R.id.rb);
        }
    }
}
