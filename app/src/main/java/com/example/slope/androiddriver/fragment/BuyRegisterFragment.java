package com.example.slope.androiddriver.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slope.androiddriver.MainActivity;
import com.example.slope.androiddriver.MyRegisteredCodeActivity;
import com.example.slope.androiddriver.R;
import com.example.slope.androiddriver.UniversalActivity;
import com.example.slope.androiddriver.basicclass.Car;
import com.example.slope.androiddriver.database.CarDataBase;
import com.example.slope.androiddriver.database.ServicesDataBase;
import com.example.slope.androiddriver.http.DataResult;
import com.example.slope.androiddriver.http.HttpResultResponse;
import com.example.slope.androiddriver.http.MyURL;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Slope on 2016/9/10.
 */
public class BuyRegisterFragment extends Fragment {
    @BindView(R.id.update)
    TextView update;

    @BindView(R.id.show_detail)
    TextView showDetail;

    @BindView(R.id.left_count)
    TextView leftCount;

    @BindView(R.id.reght_count)
    TextView reghtCount;

    @BindView(R.id.buy_m)
    RadioButton buyMonth;

    @BindView(R.id.buy_q)
    RadioButton buyQuarter;

    @BindView(R.id.buy_y)
    RadioButton buyYear;
    
    @BindView(R.id.buy_btn)
    Button buyBtn;

    @BindView(R.id.buy_btn_cancel)
    Button buyBtnCancel;

    private View rootView;
    String select;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_buy_step_one, null);

        }
        String start = this.getActivity().getIntent().getStringExtra("start");

        ViewGroup group = (ViewGroup) rootView.getRootView();
        if (group != null) {
            group.removeView(rootView);
        }
        ButterKnife.bind(this, rootView);
        init();
        buyMonth.setText("一个月");
        buyQuarter.setText("一季度");
        buyYear.setText("一年");
        if(start != null && "start".equals(start)){
//            从首页进来的显示取消按钮
            buyBtnCancel.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    private void init() {
        update.setText("已更新至：" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public boolean gotoBack() {
        return false;
    }

    List<Car> list = new ArrayList<Car>();

    @OnClick({R.id.buy_m, R.id.buy_q, R.id.buy_y, R.id.buy_btn,R.id.buy_btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buy_m:
//                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                select = (String) buyMonth.getText();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("buy", getContext().MODE_PRIVATE).edit();
                editor.putString("buy", select);
                editor.commit();
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共: 1096");
                showDetail.setText("购买一个月的使用时间 ，截止日期到 ---------科一一共: 1299");
                break;
            case R.id.buy_q:
//                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                //new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_4);
                select = (String) buyQuarter.getText();
                SharedPreferences.Editor editor1 = getActivity().getSharedPreferences("buy", getContext().MODE_PRIVATE).edit();
                editor1.putString("buy", select);
                editor1.commit();
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共:1096 ");
                showDetail.setText("购买一个季度的使用时间 ，截止日期到 ---------科一一共: 1299");

                break;
            case R.id.buy_y:
//                new ServicesDataBase(getContext()).deleteOne(CarDataBase.TABLE_NAME_1);
                select = (String) buyYear.getText();
                SharedPreferences.Editor editor2 = getActivity().getSharedPreferences("buy", getContext().MODE_PRIVATE).edit();
                editor2.putString("buy", select);
                editor2.commit();
                leftCount.setText("科一一共: 1299");
                reghtCount.setText("科四一共: 1096");
                showDetail.setText("购买一个年度的使用时间 ，截止日期到 ---------科一一共: 1299");
                break;
            case R.id.buy_btn:

//                RequestParams params1 = new RequestParams(MyURL.URL);
//                params1.addQueryStringParameter("subject", MyURL.SUBJECT1);
//                params1.addQueryStringParameter("key", MyURL.MYKEY);
//                params1.addQueryStringParameter("model", model);
//                params1.addQueryStringParameter("testType", MyURL.testType_order);
//                x.http().get(params1, new HttpResultResponse(this, getContext(), 1));
//                //加载进度条
//                showDownload.setVisibility(View.VISIBLE);
//                handler.post(updateThread);
                if(select == null){
                    Snackbar.make(view, "错误提示", Snackbar.LENGTH_INDEFINITE)
                            .setAction("请选择购买的用户数", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(v.getContext(), "请选择购买的用户数", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    return;
                }
                Intent intent = new Intent(this.getContext(), MyRegisteredCodeActivity.class);
                intent.putExtra("buy", select);
                startActivity(intent);
                break;
            case R.id.buy_btn_cancel://取消按钮
                Intent intentCancle = new Intent(this.getContext(), MainActivity.class);
                startActivity(intentCancle);
                break;
        }

    }


}
