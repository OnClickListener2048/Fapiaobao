package com.pilipa.fapiaobao.utils;

/**
 * Created by edz on 2018/1/11.
 */

public class DialogUtil {
    private static DialogUtil dialogUtil;

    private DialogUtil() {

    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

//    private void createDailog(Context context,int style,int buttonCounts,)
}
