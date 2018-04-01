package com.example.slope.androiddriver.extension.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slope.androiddriver.ExaminationActivity;
import com.example.slope.androiddriver.NewsActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.adapter.HomeRecyclerViewAdapter;
import com.example.slope.androiddriver.basicclass.News;
import com.example.slope.androiddriver.newshttp.INewsData;
import com.example.slope.androiddriver.newshttp.RequestNewsImpl;
import com.example.slope.androiddriver.newshttp.ResponseNews;
import com.example.slope.androiddriver.utils.RecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by zhou on 2018/4/1.
 */

public class SubjectListFragment extends Fragment implements INewsData {

    @BindView(R.id.news_recyclerView)
    RecyclerView newsRecyclerView;

    View rootView;

    ViewPager viewPager;

    CircleIndicator circleIndicator;

    List<News> list;

    HomeRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news,container, false);
            ButterKnife.bind(this, rootView);
            init();
        }
        return rootView;
    }

    private void init(){
        list = new ArrayList<News>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(linearLayoutManager);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        View headerView = getActivity().getLayoutInflater().inflate(R.layout.adv,newsRecyclerView,false);
        adapter = new HomeRecyclerViewAdapter(getActivity(),list);
        parseNews("|");
        newsRecyclerView.setAdapter(adapter);
        adapter.setHeaderView(headerView);
    }

    @Override
    public void NewsString(String result) {
        parseNews(result);
    }
    public void parseNews(String data){
        for (int index = 0; index < 4; index++) {
            News news = new News("科目标题" + index, "科目说明" + index, "地址" + index);
            list.add(news);
        }

        adapter.notifyDataSetChanged();
        adapter.setRecyclerViewListener(new RecyclerViewListener() {
            @Override
            public void onClick(int positon) {
                //点击进入试题练习界面
                /*Intent intent=new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("url",list.get(positon).getArticle_url());
                getActivity().startActivity(intent);*/
                String type = "Oscore";
                Intent intent = new Intent(getActivity(), ExaminationActivity.class);
                intent.putExtra("one", type);
                getActivity().startActivity(intent);


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView!=null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
    }
}
