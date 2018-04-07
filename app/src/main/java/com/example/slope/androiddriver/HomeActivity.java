package com.example.slope.androiddriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brioal.bottomtab.entity.TabEntity;
import com.brioal.bottomtab.interfaces.OnTabSelectedListener;
import com.brioal.bottomtab.view.BottomLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private BottomLayout mBottomLayout;
    private Button mBtnAdd;
    private List<TabEntity> mList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBottomLayout = (BottomLayout) findViewById(R.id.main_tab);
        /**
         *         colorNormal="@color/PrimaryDarkColor"
         colorSelected="@color/colorPrimaryDark"
         inCircleColor="@color/colorAccent"
         exCircleColor="@color/PrimaryColor"
         animDuration="2"
         */
        mBottomLayout.setColorNormal(R.color.color_00);
        mBottomLayout.setColorSelect(R.color.colorPrimaryDark);
        mBottomLayout.setInCircleColor(R.color.colorAccent);
        mBottomLayout.setExCircleColor(R.color.PrimaryColor);

        mBtnAdd = (Button) findViewById(R.id.main_add);
        initBottonLayout();
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomLayout.setNews(1, 0); //设置未读消息
                mBottomLayout.setNews(2, 1);
                mBottomLayout.setNews(3, 2);
            }
        });

    }
//asadasdaqweq
    private void initBottonLayout() {
        mList = new ArrayList<>();
        mList.add(new TabEntity(R.mipmap.car1, "首页"));
        mList.add(new TabEntity(R.mipmap.car2, "游戏"));
        mList.add(new TabEntity(R.mipmap.car3, "我的"));
        mBottomLayout.setList(mList); //设置数据源
        mBottomLayout.setNews(1, 0); //设置未读消息
        mBottomLayout.setNews(2, 1);
        mBottomLayout.setNews(3, 2);



        //设置Item点击事件
        mBottomLayout.setSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int position) {
                mBottomLayout.cleanNews(position); //清除未读消息
                if (mToast == null) {
                    mToast = Toast.makeText(HomeActivity.this, position + "", Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(position + "");
                }
                mToast.show();
            }
        });
    }
}
