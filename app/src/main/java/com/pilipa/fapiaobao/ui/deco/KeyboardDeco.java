package com.pilipa.fapiaobao.ui.deco;

import android.content.Context;

import com.example.mylibrary.utils.SizeUtils;
import com.pilipa.fapiaobao.R;
import com.pilipa.fapiaobao.base.BaseApplication;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

/**
 * Created by edz on 2018/3/6.
 */

public class KeyboardDeco extends Y_DividerItemDecoration {
    public KeyboardDeco(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
//        if (itemPosition == 0 || itemPosition == 1 || itemPosition == 2) {
//            divider = new Y_DividerBuilder()
//                    .setTopSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
//                    .create();
//            return divider;
//        }

        switch (itemPosition % 3) {
            case 0:
            case 1:
                //每一行第一个和第二个显示rignt和bottom
                if (itemPosition > 2) {
                    divider = new Y_DividerBuilder()
                            .setRightSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .setBottomSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setTopSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .setRightSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .setBottomSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .create();
                }

                break;
            case 2:
                //最后一个只显示bottom
                if (itemPosition == 2) {
                    divider = new Y_DividerBuilder()
                            .setTopSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .setBottomSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .create();
                } else {
                    divider = new Y_DividerBuilder()
                            .setBottomSideLine(true, BaseApplication.context().getResources().getColor(R.color.gray_line), SizeUtils.dp2px(0.5f), 0, 0)
                            .create();
                }

                break;
            default:
                break;
        }
        return divider;
    }
}
