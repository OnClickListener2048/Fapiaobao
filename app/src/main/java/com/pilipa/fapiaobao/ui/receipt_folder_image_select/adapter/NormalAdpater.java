package com.pilipa.fapiaobao.ui.receipt_folder_image_select.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.ui.model.Image;

import java.util.ArrayList;


/**
 * @author dagou
 * @date 2017/9/29
 */

public class NormalAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 208;
    private static final int TYPE_CAPTURE = 189;

    private static final String TAG = "NormalAdpater";
    public static final String EXTRA_ALL_DATA = "all_data";
    public static final String EXTRA_CURRENT_POSITION = "current_item_position";
    public static final String EXTRA_BUNDLE = "extra_bundle";

    private ArrayList<Image> arrayList;
    private RequestManager requestManager;
    private int mImageResize;
    private OnImageSelectListener onImageSelectListener;
    private OnImageClickListener onImageClickListener;

    public NormalAdpater(ArrayList<Image> images, int imageResize) {
        this.arrayList = images;
        this.mImageResize = imageResize;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        requestManager = Glide.with(parent.getContext());
        View view;
        if (viewType == TYPE_NORMAL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_receipt_folder_image, parent, false);
            return new VH(view);
        } else if (viewType == TYPE_CAPTURE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload_myreceipt_capture, parent, false);
            return new CaptureViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE_NORMAL;

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof VH) {
            final VH holder = (VH) viewHolder;
            final Image image = arrayList.get(position);
            image.position = position;
            Log.d(TAG, "onBindViewHolder:   image.position"+  image.position);
//            if (image.isSelected) {
//                holder.radioButton.setText("已选中");
//            } else {
//                holder.radioButton.setText("未选中");
//            }
            holder.radioButton.setChecked(image.isSelected);

            requestManager
                    .load(image.path)
                    .asBitmap()
                    .placeholder(R.mipmap.loading_small)
                    .error(R.mipmap.error_small)
                    .override(mImageResize, mImageResize)
                    .thumbnail(0.1f)
                    .into(holder.iv_image_item);
//            holder.radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (view.isSelected()) {
//                        view.setSelected(false);
//                        image.isSelected = false;
////                        holder.radioButton.setText("未选中");
//                        if (onImageSelectListener != null) {
//                            onImageSelectListener.onImageSelect(image);
//                        }
//                        Log.d(TAG, "onClick:image.isSelected" + image.isSelected);
//                    } else {
//                        view.setSelected(true);
//                        image.isSelected = true;
////                        holder.radioButton.setText("已选中");
//                        if (onImageSelectListener != null) {
//                            onImageSelectListener.onImageSelect(image);
//                        }
//                        Log.d(TAG, "onClick: image.isSelected" + image.isSelected);
//                    }
//                }
//            });

            holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    image.isSelected = isChecked;
                    if (onImageSelectListener != null) {
                        Log.d(TAG, "onCheckedChanged: image.position"+image.position);
                        onImageSelectListener.onImageSelect(image);
                    }
                }
            });

            holder.iv_image_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onImageClickListener.onImageClick(arrayList, image.position);
                }
            });
        } else if (viewHolder instanceof CaptureViewHolder) {
//            CaptureViewHolder captureViewHolder = (CaptureViewHolder) viewHolder;
//            captureViewHolder.mHint.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (view.getContext() instanceof OnPhotoCapture) {
//                        ((OnPhotoCapture) view.getContext()).capture();
//                    }
//                }
//            });
        }
    }

    public void refresh(ArrayList<Image> allItemList) {
        arrayList = allItemList;
        notifyDataSetChanged();
    }

    public interface OnImageClickListener {
        void onImageClick(ArrayList<Image> allItemList, int position);
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnPhotoCapture {
        void capture();
    }


    public interface OnImageSelectListener {
        void onImageSelect(Image image);
    }

    public void setOnImageSelectListener(OnImageSelectListener onImageSelectListener) {
        this.onImageSelectListener = onImageSelectListener;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {


        private final ImageView iv_image_item;
        private final CheckBox radioButton;

        public VH(View itemView) {
            super(itemView);
            iv_image_item = (ImageView) itemView.findViewById(R.id.iv_image_item);
            radioButton = (CheckBox) itemView.findViewById(R.id.rb);
        }
    }

    private static class CaptureViewHolder extends RecyclerView.ViewHolder {

        private TextView mHint;

        CaptureViewHolder(View itemView) {
            super(itemView);

        }
    }
}
