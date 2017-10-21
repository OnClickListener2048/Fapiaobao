package com.pilipa.fapiaobao.ui.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edz on 2017/10/20.
 */

public class Image implements Parcelable {
    public String path;
    public String name;
    public int position;
    public boolean isSelected = false;
    public boolean isFromNet;
    public boolean isCapture;
    public Uri uri;
    public Image() {

    }


    protected Image(Parcel in) {
        path = in.readString();
        name = in.readString();
        position = in.readInt();
        isSelected = in.readByte() != 0;
        isFromNet = in.readByte() != 0;
        isCapture = in.readByte() != 0;
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(name);
        dest.writeInt(position);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeByte((byte) (isFromNet ? 1 : 0));
        dest.writeByte((byte) (isCapture ? 1 : 0));
        dest.writeParcelable(uri, flags);
    }
}
