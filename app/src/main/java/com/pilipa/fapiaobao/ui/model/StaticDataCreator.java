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
        list.clear();
        list.add(context.getString(R.string.processing));
        list.add(context.getString(R.string.finished));
        list.add(context.getString(R.string.closed));
        return list;
    }

    public static List initCompanyManagerTabData(Context context) {
        if (list == null) {
            list = new ArrayList();
        }
        list.clear();
        list.add(context.getString(R.string.my_collection));
        list.add(context.getString(R.string.my_company));
        return list;
    }
    public static List initReceiptFolderTabData(Context context) {
        if (list == null) {
            list = new ArrayList();
        }
        list.clear();
        list.add(context.getString(R.string.provided));
        list.add(context.getString(R.string.not_used));
        return list;
    }

    public static ArrayList<String> initReceiptKindData(Context context) {
        ArrayList<String> list = new ArrayList();

        list.clear();
        list.add(context.getString(R.string.paper_normal_receipt));
        list.add(context.getString(R.string.paper_special_receipt));
        list.add(context.getString(R.string.paper_elec_receipt));
        list.add(context.getString(R.string.ofAll));
        return list;
    }


}
