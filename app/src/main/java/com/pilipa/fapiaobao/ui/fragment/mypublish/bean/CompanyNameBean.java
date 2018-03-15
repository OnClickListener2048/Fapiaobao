package com.pilipa.fapiaobao.ui.fragment.mypublish.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pilipa.fapiaobao.net.Constant;
import com.pilipa.fapiaobao.net.bean.me.demandlist.DemandListItem;

/**
 * Created by edz on 2018/3/14.
 */

public class CompanyNameBean extends AbstractExpandableItem<DemandListItem.ListBean> implements MultiItemEntity {
    private String name;

    @Override
    public int getItemType() {
        return Constant.TYPE_COMPANY_NAME;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
