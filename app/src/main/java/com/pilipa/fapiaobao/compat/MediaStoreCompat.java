package com.pilipa.fapiaobao.compat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.os.EnvironmentCompat;

import com.example.mylibrary.utils.TLog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by edz on 2017/10/20.
 */

public class MediaStoreCompat {
    private final WeakReference<Activity> mContext;
    private final Fragment mFragment;
    private Uri mCurrentPhotoUri;
    private String mCurrentPhotoPath;
    public static String authority = "com.pilipa.fapiaobao.fileprovider";

    public MediaStoreCompat(Activity activity) {
        mContext = new WeakReference<>(activity);
        mFragment = null;
    }

    public MediaStoreCompat(Activity activity, Fragment fragment) {
        mContext = new WeakReference<>(activity);
        mFragment = fragment;
    }


    public static boolean hasCameraFeature(Context context) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


    public void dispatchCaptureIntent(final Activity activity,final int requestCode) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (captureIntent.resolveActivity(activity.getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (photoFile != null) {
                            mCurrentPhotoPath = photoFile.getAbsolutePath();
                            mCurrentPhotoUri = FileProvider.getUriForFile(mContext.get(),
                                    authority, photoFile);
                            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);
                            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                List<ResolveInfo> resInfoList = activity.getPackageManager()
                                        .queryIntentActivities(captureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                                for (ResolveInfo resolveInfo : resInfoList) {
                                    String packageName = resolveInfo.activityInfo.packageName;
                                    activity.grantUriPermission(packageName, mCurrentPhotoUri,
                                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                }
                            }

                            if (mFragment != null) {
                                mFragment.startActivityForResult(captureIntent, requestCode);
                            } else {
                                mContext.get().startActivityForResult(captureIntent, requestCode);
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = String.format("JPEG_%s.jpg", timeStamp);

        String extDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath() + File.separator + "fapiaobao"+File.separator;
        File extDirFile = new File(extDir);
        extDirFile.mkdirs();

        TLog.log("extDirFile.exists"+extDirFile.exists());
        // Avoid joining path components manually
        File tempFile = new File(extDirFile, imageFileName);
        TLog.log("tempFile.exists"+tempFile.exists());
        // Handle the situation that user's external storage is not ready
        if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
            return null;
        }

        return tempFile;
    }

    public Uri getCurrentPhotoUri() {
        return mCurrentPhotoUri;
    }

    public String getCurrentPhotoPath() {
        return mCurrentPhotoPath;
    }
}
