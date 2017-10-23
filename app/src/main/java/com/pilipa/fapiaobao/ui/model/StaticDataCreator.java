package com.pilipa.fapiaobao.ui.model;

import android.content.Context;

import com.pilipa.fapiaobao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/16.
 */

public class StaticDataCreator {
   public static List <String>list;

    public static List initMyPublishTabData(Context context) {
        if (list == null) {
            list = new ArrayList();
        }
        list.add(context.getString(R.string.processing));
        list.add(context.getString(R.string.finished));
        list.add(context.getString(R.string.closed));
        return list;
    }
}