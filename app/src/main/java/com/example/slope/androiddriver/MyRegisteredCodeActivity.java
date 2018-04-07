package com.example.slope.androiddriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slope.androiddriver.utils.DES;
import com.example.slope.androiddriver.utils.DateUtil;
import com.example.slope.androiddriver.utils.PhoneInfoUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 2018/4/7.
 */
public class MyRegisteredCodeActivity extends AppCompatActivity {

    @BindView(R.id.register_code)
    EditText registerCode;

    @BindView(R.id.register_password)
    EditText registerPassword;

    @BindView(R.id.btn_register_it)
    Button btnRegisterIt;

    String decryptPwd = "";
    String month = "";
    String buyType = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_step_two);
        ButterKnife.bind(this);
        this.generateCode();
    }

    /**
     * 生产出一串密文激活码
     */
    private void generateCode() {
        Intent intent = getIntent();
        buyType = intent.getStringExtra("buy");
        if(buyType == null){
            return;
        }
        String format = DateUtil.format(new Date(), DateUtil.DATE_PATTERN.YYYY_MM);
        switch (buyType){
            case "一个月" :
                month = DateUtil.AddMonth(format, 1);
                break;
            case"一季度" :
                month = DateUtil.AddMonth(format, 3);
                break;
            case"一年" :
                month = DateUtil.AddMonth(format, 12);
                break;
        }

        String key = "ZHOU-PC9";//最长8位
        Map<String,String> map = new HashMap<>();
        PhoneInfoUtil.getPhoneInfo(map);
        String id = map.get("ID");
        String brand = map.get("BRAND");
        //ID + brand + buyType + month
        String text = id.concat(brand).concat(buyType).concat(month);

        String encryptCode = "";
        try {
            encryptCode = DES.encryptDES(text,key);
            decryptPwd = DES.decryptDES(encryptCode, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerCode.setText(encryptCode);

        SharedPreferences.Editor editor = this.getSharedPreferences("encryptCode", Context.MODE_PRIVATE).edit();
        editor.putString("decryptPwd", decryptPwd);
        editor.commit();
    }

    @OnClick(R.id.btn_register_it)
    public void onClick(final View view) {
        hideKeyboard();//隐藏键盘
        if(buyType == null){
            Snackbar.make(view, "错误提示", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请选择购买的用户数", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MyRegisteredCodeActivity.this, "请选择购买的用户数", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            return;
        }

        if (registerCode.length() == 0) {
            Snackbar.make(view, "错误提示", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请不要删掉注册密码，你可以重新打开购买页面获取注册密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MyRegisteredCodeActivity.this, "请不要删掉注册密码，你可以重新打开购买页面获取注册密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerCode.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }

        if (registerPassword.length() == 0) {
            Snackbar.make(view, "错误提示", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写注册密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MyRegisteredCodeActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        //校验输入的是否正确
        if(decryptPwd.equals(registerPassword)){
            SharedPreferences.Editor editor = this.getSharedPreferences("verify", Context.MODE_PRIVATE).edit();
            editor.putString("verify", "success");
            editor.putString("month", month);
            editor.commit();
            StringBuilder stringBuilder = new StringBuilder("恭喜你激活成功！").append("使用到期日：").append(month);
            Toast.makeText(MyRegisteredCodeActivity.this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        }else {
            Snackbar.make(view, "错误提示", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写正确的注册密钥", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MyRegisteredCodeActivity.this, "请填写正确的注册密钥", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }

    }

    /**
     * 隐藏键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
