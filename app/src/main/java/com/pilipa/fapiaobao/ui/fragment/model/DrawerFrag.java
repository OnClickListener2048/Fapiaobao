package com.pilipa.fapiaobao.ui.fragment.model;

import android.content.Context;

import com.pilipa.fapiaobao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyt on 2017/10/14.
 */

public class DrawerFrag {
    public static ArrayList<String> list;

    public static ArrayList<String> initData(Context context) {
        list = new ArrayList<>();
        list.add(context.getString(R.string.texi));
        list.add(context.getString(R.string.subway));
        list.add(context.getString(R.string.express));
        list.add(context.getString(R.string.hotel));
        list.add(context.getString(R.string.phone));
        list.add(context.getString(R.string.food));
        list.add(context.getString(R.string.gas));
        return list;
    }
}
