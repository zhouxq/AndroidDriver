package com.example.slope.androiddriver;
import java.util.List;

import java.util.ArrayList;

import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
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
    private android.app.LoaderManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        examScanView = (ExamScanView) findViewById(R.id.scanview);
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 8; i++){
            items.add("ตฺ 卡e" + (i + 1) + " าณ");
        }
        //action bar 去掉下面的阴影
        if(Build.VERSION.SDK_INT >= 21){
            getSupportActionBar().setElevation(0);
        }
        try {
//            Selector<Subject> subjectSelector = DbHelper.db.selector(Subject.class);
//            TableEntity<Subject> tableEntity = subjectSelector.getTable();
//            tableEntity.tableIsExist();
            /*Cursor cursor = DbHelper.getDb().execQuery("select * from subject");

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
            }*/


            List<Subject> subjectList = DbHelper.getDb().selector(Subject.class)
//                    .where("name","like","%kevin%")
//                    .and("email", "=", "caolbmail@gmail.com")
//                    .orderBy("regTime",true)
                    .limit(2) //只查询两条记录
//                    .offset(2) //偏移两个,从第三个记录开始返回,limit配合offset达到sqlite的limit m,n的查询
                    .findAll();
            for (Subject subject : subjectList) {
                System.out.println(subject.toString());
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
// 获取Loader管理器。http://www.cnblogs.com/plokmju/p/android_Loaders.html
        manager = getLoaderManager();
        // 初始化并启动一个Loader，设定标识为1000，并制定一个回调函数。
        manager.initLoader(1000, null, callbacks);
//        this.findAllSubject(this);
        
        adapter = new ExamViewAdapterExam(this, items);
        examScanView.setAdapter(adapter);
    }

    /**
     *
     */
    private LoaderManager.LoaderCallbacks<Object> callbacks = new LoaderManager.LoaderCallbacks<Object>() {

        @Override
        public Loader<Object> onCreateLoader(int i, Bundle bundle) {
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Object> loader, Object o) {

        }

        @Override
        public void onLoaderReset(Loader<Object> loader) {

        }
    };

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