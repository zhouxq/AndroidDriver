package com.example.slope.androiddriver.adapter;

import android.view.View;

/**
 * Created by zhou on 2018/4/8.
 */

public abstract class ExamPageAdapter
{
    /**
     * @return 页面view
     */
    public abstract View getView();

    public abstract int getCount();

    /**
     * 将内容添加到view中
     *
     * @param view
     *            包含内容的view
     * @param position
     *            第position页
     */
    public abstract void addContent(View view, int position);
}