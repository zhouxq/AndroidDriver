package com.example.slope.androiddriver;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.slope.androiddriver.fragment.Fragment1;
import com.example.slope.androiddriver.fragment.Fragment2;
import com.example.slope.androiddriver.fragment.Fragment3;
import com.example.slope.androiddriver.fragment.Fragment4;
import com.example.slope.androiddriver.widget.BottomTabView;

import java.util.ArrayList;

public class Home2Activity extends AppCompatActivity {

    BottomTabView bottomTabView;

    ViewPager viewPager;

    FragmentPagerAdapter adapter;

    ArrayList<Fragment> fragments = new ArrayList<>();

    ArrayList<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        bottomTabView = (BottomTabView) findViewById(R.id.bottomTabView);

        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(adapter);

        tabItemViews.add(new BottomTabView.TabItemView(this, "标题1", R.color.colorPrimary, R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题2", R.color.colorPrimary, R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题3", R.color.colorPrimary, R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题4", R.color.colorPrimary, R.color.colorAccent, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round));

        bottomTabView.setTabItemViews(tabItemViews);

        bottomTabView.setUpWithViewPager(viewPager);
    }

}
