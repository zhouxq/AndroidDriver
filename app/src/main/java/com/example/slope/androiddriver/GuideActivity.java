package com.example.slope.androiddriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.slope.androiddriver.adapter.ViewPagerGuideAdapter;
import com.example.slope.androiddriver.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slope on 2016/9/29.
 */

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewPager ;
    private List<ImageView> viewPagerData ;
    private PagerAdapter viewPagerAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViewPager() ;
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.guide_viewpager) ;
        viewPagerData = new ArrayList<ImageView>() ;
        int[] imgs = {R.mipmap.timg2 , R.mipmap.timg3 , R.mipmap.timg4} ;
        for(int i = 0 ; i <imgs.length ;i++) {
            ImageView iv = new ImageView(this) ;
            iv.setImageResource(imgs[i]) ;
            iv.setScaleType(ImageView.ScaleType.FIT_XY) ;
            viewPagerData.add(iv) ;
            if(i == imgs.length - 1) {
                iv.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //输入激活码部分；未激活的只有部分功能
                        Intent intent = new Intent(GuideActivity.this,
                                UniversalActivity.class)
                                .putExtra("start", "start").putExtra("title", "购买激活码");
                        startActivity(intent) ;
                        finish();
                    }
                }) ;
            }
        }
        viewPagerAdapter = new ViewPagerGuideAdapter(viewPagerData) ;
        viewPager.setAdapter(viewPagerAdapter) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedUtils.saveNotFirst(this) ;
    }
}
