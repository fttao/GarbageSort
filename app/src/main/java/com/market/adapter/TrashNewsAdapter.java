package com.market.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.market.R;
import com.market.model.TrashNewsResponse;

import java.util.List;

/**
 * author: ft
 * created on: 2022/8/24 14:43
 * description:
 */
public class TrashNewsAdapter extends BaseQuickAdapter<TrashNewsResponse.NewslistBean, BaseViewHolder> {

    public TrashNewsAdapter(int layoutResId, @Nullable List<TrashNewsResponse.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrashNewsResponse.NewslistBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_description, item.getDescription())
                .addOnClickListener(R.id.item_trash_news);
    }
}
