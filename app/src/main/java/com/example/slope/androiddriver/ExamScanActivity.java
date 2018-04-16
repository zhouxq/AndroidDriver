package com.example.slope.androiddriver;
import java.util.HashMap;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;

import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.example.slope.androiddriver.adapter.ExamViewAdapterExam;
import com.example.slope.androiddriver.database.DbHelper;
import com.example.slope.androiddriver.database.DbUtil;
import com.example.slope.androiddriver.drawer.ExamScanView;
import com.example.slope.androiddriver.entity.Subject;

import org.xutils.ex.DbException;

/**
 * Created by zhou on 2018/4/8.
 */

public class ExamScanActivity extends AppCompatActivity
{
    ExamScanView examScanView;
    ExamViewAdapterExam adapter;
    List<Subject> subjectList;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    ArrayList llist = bundle.getParcelableArrayList("llist");
//                    final List<Map<String,Object>> mylist=
//                            (List<Map<String,Object>>) llist.get(0);
                    mHandler.removeCallbacks(mRunnable);
                    /*List<String> items = new ArrayList<String>();

                    for (int i = 0; i < 8; i++){
                        items.add("ตฺ 卡e" + (i + 1) + " าณ");
                    }
                    adapter = new ExamViewAdapterExam(ExamScanActivity.this,items);

                    examScanView.setAdapter(adapter);*/
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private Runnable mRunnable = new Runnable() {
        public void run() {
            try {
                Message msg = mHandler.obtainMessage();
                subjectList = DbHelper.getDb().selector(Subject.class)
                    .limit(2) //只查询两条记录
                    .findAll();
                for (Subject subject : subjectList) {
                    System.out.println(subject.toString());
                }
                msg.what = 1 ;
                Bundle bundle = new Bundle();



                ArrayList llist = new ArrayList();//ArrayList 继承自ParcelableArrayList
                List<Map<String,Object>> mylist = new ArrayList<>();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("123",new Object());
                mylist.add(map);
                llist.add(mylist);//mylist是List<Map<String,Object>>类型的对象

                bundle.putParcelableArrayList("llist", llist);
                mHandler.sendMessage(msg);//发送至主界面显示
            } catch (DbException e) {
                e.printStackTrace();
            }            // 每3秒执行一次
//            mHandler.postDelayed(mRunnable, 3000);  //给自己发送消息，自运行
        }
    };

    @Override
    protected void onDestroy() {
        //将线程销毁掉
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        examScanView = (ExamScanView) findViewById(R.id.scanview);

        //action bar 去掉下面的阴影
        if(Build.VERSION.SDK_INT >= 21){
            getSupportActionBar().setElevation(0);
        }
        List<String> items = new ArrayList<String>();

        for (int i = 0; i < 8; i++){
            items.add("ตฺ 卡e" + (i + 1) + " าณ");
        }
        adapter = new ExamViewAdapterExam(ExamScanActivity.this,items);

        examScanView.setAdapter(adapter);
        // 通过Handler启动线程
//        mHandler.post(mRunnable);  //发送消息，启动线程运行


    }


    /**
     *
     * @param context
     * @return
     */
    public static List<Subject> findAllSubject(Context context) {
        List<Subject> Subjects = new ArrayList<Subject>();
        SQLiteDatabase myDateBase = DbUtil.openDatabase(context);
        String sql = "select * from subject";
        try {
            Cursor cursor = myDateBase.rawQuery(sql, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Subject subject = new Subject();
                subject.setId(cursor.getInt(cursor.getColumnIndex("id")));
                subject.setTitle(cursor.getString(cursor.getColumnIndex("title")));
//                Subjects.add(Subject);
                System.out.println(cursor.getColumnIndex("title"));
                System.out.println(subject.toString());
                cursor.moveToNext();
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
            if (myDateBase.isOpen()) {
                myDateBase.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Subjects;
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int action = event.getKeyCode();
        if (action == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(ExamScanActivity.this)
            .setMessage("真的退出练习?")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ExamScanActivity.this, "退出成功，即将返回主界面", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ExamScanActivity.this,MainActivity.class));
                    finish();
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}