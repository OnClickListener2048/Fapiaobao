package com.pilipa.fapiaobao.databinding.base.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pilipa.fapiaobao.R;

/**
 * Created by edz on 2018/4/2.
 */

public class BaseTitleBar {
    public ViewGroup toolView;
    public View left, center, right;

    private View.OnClickListener listener;
    private View.OnClickListener rightListener;
    private View.OnClickListener centerListener;

    public BaseTitleBar(Context context, ViewGroup toolView, int layoutId) {
        this.toolView = toolView;
        toolView.setPadding(0, 0, 0, 0);
        ViewGroup toolLayout = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, toolView, false);
        toolView.addView(toolLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initTitleBar(toolLayout);
    }


    private void initTitleBar(View toolView) {
        left = toolView.findViewById(R.id.title_left);
        center = toolView.findViewById(R.id.title_center);
        right = toolView.findViewById(R.id.title_right);

        if (left != null) {
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });

            left.setOnClickListener(view -> {

            });


        }

        if (right != null) {
            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rightListener != null) {
                        rightListener.onClick(v);
                    }
                }
            });
        }

        if (center != null) {
            center.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (centerListener != null) {
                        centerListener.onClick(v);
                    }
                }
            });
        }
    }

    public void setTitleText(String titleText) {
        if (titleText == null || titleText.trim().equals("")) {
            return;
        }

        if (center != null && center instanceof TextView) {
            ((TextView) center).setText(titleText);
        }
    }

    public void setTitleText(int stringId) {
        if (center != null && center instanceof TextView) {
            ((TextView) center).setText(stringId);
        }
    }

    public void setRightText(int stringId) {
        if (right != null && right instanceof TextView) {
            ((TextView) right).setText(stringId);
        }
    }

    public void setRightText(String rightText) {
        if (rightText == null || rightText.trim().equals("")) {
            return;
        }

        if (right != null && right instanceof TextView) {
            ((TextView) right).setText(rightText);
        }
    }

    public void setLeftVisible(boolean visible) {
        if (left != null) {
            left.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void setRightVisible(boolean visible) {
        if (right != null) {
            right.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void setOnLeftClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setOnRightClickListener(View.OnClickListener listener) {
        rightListener = listener;
    }

    public void setOnCenterClickListener(View.OnClickListener listener) {
        centerListener = listener;
    }
}

