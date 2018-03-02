package com.pilipa.fapiaobao.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.pilipa.fapiaobao.utils.StreamUtils.close;
import static com.pilipa.fapiaobao.utils.TDevice.isDownloadsDocument;
import static com.pilipa.fapiaobao.utils.TDevice.isExternalStorageDocument;
import static com.pilipa.fapiaobao.utils.TDevice.isGooglePhotosUri;
import static com.pilipa.fapiaobao.utils.TDevice.isMediaDocument;
import static com.pilipa.fapiaobao.utils.UriToPath.getDataColumn;

/**
 * Created by edz on 2017/10/27.
 */

public class BitmapUtils {
    /**
     * A default size to use to increase hit rates when the required size isn't defined.
     * Currently 64KB.
     */
    public final static int DEFAULT_BUFFER_SIZE = 64 * 1024;

    /**
     * 创建一个图片处理Options
     *
     * @return {@link android.graphics.BitmapFactory.Options}
     */
    public static BitmapFactory.Options createOptions() {
        return new BitmapFactory.Options();
    }

    /**
     * 把一个{@link android.graphics.BitmapFactory.Options}进行参数复原操作，
     * 避免重复创建新的 {@link android.graphics.BitmapFactory.Options}
     *
     * @param options {@link android.graphics.BitmapFactory.Options}
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;

        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            options.inBitmap = null;
            options.inMutable = true;
        }
    }

    /**
     * 获取图片的真实后缀
     *
     * @param filePath 图片存储地址
     * @return 图片类型后缀
     */
    public static String getExtension(String filePath) {
        BitmapFactory.Options options = createOptions();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        String mimeType = options.outMimeType;
        return mimeType.substring(mimeType.lastIndexOf("/") + 1);
    }

    public static Bitmap decodeBitmap(final File file,
                                      final int maxWidth,
                                      final int maxHeight,
                                      byte[] byteStorage,
                                      BitmapFactory.Options options,
                                      boolean exactDecode) {
        InputStream is;
        try {
            // In this, we can set the buffer size
            is = new BufferedInputStream(new FileInputStream(file),
                    byteStorage == null ? DEFAULT_BUFFER_SIZE : byteStorage.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        if (options == null)
            options = createOptions();
        else
            resetOptions(options);

        // First decode with inJustDecodeBounds=true to check dimensions
        options.inJustDecodeBounds = true;

        // 5MB. This is the max image header size we can handle, we preallocate a much smaller buffer
        // but will resize up to this amount if necessary.
        is.mark(5 * 1024 * 1024);
        BitmapFactory.decodeStream(is, null, options);

        // Reset the inputStream
        try {
            is.reset();
        } catch (IOException e) {
            e.printStackTrace();
            close(is);
            resetOptions(options);
            return null;
        }

        // Calculate inSampleSize
        calculateScaling(options, maxWidth, maxHeight, exactDecode);

        // Init the BitmapFactory.Options.inTempStorage value
        if (byteStorage == null)
            byteStorage = new byte[DEFAULT_BUFFER_SIZE];
        options.inTempStorage = byteStorage;

        // Decode bitmap with inSampleSize set FALSE
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);

        // Close the Stream
        close(is);
        // And Reset the option
        resetOptions(options);

        // To scale bitmap to user set
        bitmap = scaleBitmap(bitmap, maxWidth, maxHeight, true);

        return bitmap;

    }

    /**
     * 按长宽比缩小一个Bitmap
     *
     * @param source        待缩小的{@link Bitmap}
     * @param scale         缩放比0～1，1代表不缩放
     * @param recycleSource 是否释放Bitmap源
     * @return 一个缩小后的Bitmap
     */
    public static Bitmap scaleBitmap(Bitmap source, float scale, boolean recycleSource) {
        if (scale <= 0 || scale >= 1)
            return source;
        Matrix m = new Matrix();
        final int width = source.getWidth();
        final int height = source.getHeight();
        m.setScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(source, 0, 0, width, height, m, false);
        if (recycleSource)
            source.recycle();
        return scaledBitmap;
    }

    /**
     * 按照长宽比缩小一个Bitmap到指定尺寸，
     * 当传入的高宽都大于原始值时将不做缩小操作
     *
     * @param source          待缩小的{@link Bitmap}
     * @param targetMaxWidth  目标宽度
     * @param targetMaxHeight 目标高度
     * @param recycleSource   是否释放Bitmap源
     * @return 一个缩小后的Bitmap
     */
    public static Bitmap scaleBitmap(Bitmap source, int targetMaxWidth, int targetMaxHeight, boolean recycleSource) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        Bitmap scaledBitmap = source;
        if (sourceWidth > targetMaxWidth || sourceHeight > targetMaxHeight) {
            float minScale = Math.min(targetMaxWidth / (float) sourceWidth,
                    targetMaxHeight / (float) sourceHeight);
            scaledBitmap = Bitmap.createScaledBitmap(scaledBitmap,
                    (int) (sourceWidth * minScale),
                    (int) (sourceHeight * minScale), false);
            if (recycleSource)
                source.recycle();
        }

        return scaledBitmap;
    }

    /**
     * 通过{@link android.graphics.BitmapFactory.Options}计算图片的缩放比,
     * 并将缩放后的信息存储在传入的{@link android.graphics.BitmapFactory.Options}中，
     * 以便后续的 {@link BitmapFactory#decodeStream(InputStream)}等操作
     *
     * @param options            一个图片的{@link android.graphics.BitmapFactory.Options}， 含有图片的基础信息
     * @param requestedMaxWidth  目标宽度
     * @param requestedMaxHeight 目标高度
     * @param exactDecode        是否精确计算，该参数只在 {@link android.os.Build.VERSION#SDK_INT} 大于 Api19 时有效
     */
    private static BitmapFactory.Options calculateScaling(BitmapFactory.Options options,
                                                          final int requestedMaxWidth,
                                                          final int requestedMaxHeight,
                                                          boolean exactDecode) {
        int sourceWidth = options.outWidth;
        int sourceHeight = options.outHeight;

        if (sourceWidth <= requestedMaxWidth && sourceHeight <= requestedMaxHeight) {
            return options;
        }

        final float maxFloatFactor = Math.max(sourceHeight / (float) requestedMaxHeight,
                sourceWidth / (float) requestedMaxWidth);
        final int maxIntegerFactor = (int) Math.floor(maxFloatFactor);
        final int lesserOrEqualSampleSize = Math.max(1, Integer.highestOneBit(maxIntegerFactor));

        options.inSampleSize = lesserOrEqualSampleSize;
        // Density scaling is only supported if inBitmap is null prior to KitKat. Avoid setting
        // densities here so we calculate the final Bitmap size correctly.
        if (exactDecode && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            float scaleSize = sourceWidth / (float) lesserOrEqualSampleSize;
            float outSize = sourceWidth / maxFloatFactor;

            options.inTargetDensity = 1000;
            options.inDensity = (int) (1000 * (scaleSize / outSize) + 0.5);

            // If isScaling
            if (options.inTargetDensity != options.inDensity) {
                options.inScaled = true;
            } else {
                options.inDensity = options.inTargetDensity = 0;
            }
        }
        return options;
    }

    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 150) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        System.out.println("path=" + path);

        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
        * 旋转图片
        * @param angle
        * @param bitmap
        * @return Bitmap
        */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static String getRealFilePath(Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }

                }
                cursor.close();
            }
            if (data == null) {
                data = getImageAbsolutePath(context, uri);
            }

        }
        return data;
    }

    /**
     * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     *
     * @param context
     * @param imageUri
     * @author yaoxing
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[] { split[1] };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public final static class Compressor {

        public static File compressImage(final File sourceFile, final long maxSize,
                                         final int minQuality, final int maxWidth,
                                         final int maxHeight) {
            return compressImage(sourceFile, maxSize, minQuality, maxWidth, maxHeight, true);
        }

        public static File compressImage(final File sourceFile, final long maxSize,
                                         final int minQuality, final int maxWidth,
                                         final int maxHeight, boolean exactDecode) {
            return compressImage(sourceFile, maxSize, minQuality, maxWidth, maxHeight, null, null, exactDecode);
        }


        /**
         * 压缩图片
         *
         * @param sourceFile  原图地址
         * @param maxSize     最大文件地址byte
         * @param minQuality  最小质量
         * @param maxWidth    最大宽度
         * @param maxHeight   最大高度
         * @param byteStorage 用于批量压缩时的buffer，不必要为null，
         *                    需要时，推荐 {{@link #DEFAULT_BUFFER_SIZE}}
         * @param options     批量压缩时复用参数，可调用 {{@link #createOptions()}} 得到
         * @param exactDecode 是否精确解码， TRUE： 在4.4及其以上机器中能更节约内存
         * @return 返回压缩后的图片文件，该图片存储在原图同级目录下，以compress.temp结尾
         */
        public static File compressImage(final File sourceFile,
                                         final long maxSize,
                                         final int minQuality,
                                         final int maxWidth,
                                         final int maxHeight,
                                         byte[] byteStorage,
                                         BitmapFactory.Options options,
                                         boolean exactDecode) {
            // build source file
            if (sourceFile == null || !sourceFile.exists() || !sourceFile.canRead())
                return null;

            // create new temp file
            final File tempFile = new File(sourceFile.getParent(),
                    String.format("compress_%s.temp", System.currentTimeMillis()));

            if (!tempFile.exists()) {
                try {
                    if (!tempFile.createNewFile())
                        return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            // build to bitmap
            Bitmap bitmap = decodeBitmap(sourceFile, maxWidth, maxHeight, byteStorage, options, exactDecode);
            if (bitmap == null)
                return null;

            // Get the bitmap format
            Bitmap.CompressFormat compressFormat = bitmap.hasAlpha() ?
                    Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG;

            // Write to out put file
            boolean isOk = false;
            for (int i = 1; i <= 10; i++) {
                // In this we change the quality start 92%
                int quality = 92;
                for (; ; ) {
                    BufferedOutputStream outputStream = null;
                    try {
                        outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
                        bitmap.compress(compressFormat, quality, outputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // on IOException we need recycle the bitmap
                        bitmap.recycle();
                        return null;
                    } finally {
                        close(outputStream);
                    }
                    // Check file size
                    long outSize = tempFile.length();
                    if (outSize <= maxSize) {
                        isOk = true;
                        break;
                    }
                    if (quality < minQuality)
                        break;
                    quality--;
                }

                if (isOk) {
                    break;
                } else {
                    // If not ok, we need scale the Bitmap to small
                    // In this, once subtract 2%, most 20%
                    bitmap = scaleBitmap(bitmap, 1 - (0.2f * i), true);
                }
            }
            // recycle bitmap
            bitmap.recycle();

            // The end, If not success, return false
            if (!isOk)
                return null;

            // Rename to out file
            return tempFile;
        }
    }


}
