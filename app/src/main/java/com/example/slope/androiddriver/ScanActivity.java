package com.example.slope.androiddriver;
import java.util.List;

import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;

import com.example.slope.androiddriver.adapter.ScanViewAdapter;
import com.example.slope.androiddriver.database.DbHelper;
import com.example.slope.androiddriver.drawer.ScanView;
import com.example.slope.androiddriver.entity.Subject;

import org.xutils.db.Selector;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;

/**
 * Created by zhou on 2018/4/8.
 */

public class ScanActivity extends AppCompatActivity
{
    ScanView scanview;
    ScanViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scanview = (ScanView) findViewById(R.id.scanview);
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 8; i++){
            items.add("ตฺ " + (i + 1) + " าณ");
        }
        try {
            Selector<Subject> subjectSelector = DbHelper.db.selector(Subject.class);
            TableEntity<Subject> tableEntity = subjectSelector.getTable();
            tableEntity.tableIsExist();
            List<Subject> subjectList = DbHelper.db.findAll(Subject.class);


        } catch (DbException e) {
            e.printStackTrace();
        }
        adapter = new ScanViewAdapter(this, items);
        scanview.setAdapter(adapter);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int action = event.getKeyCode();
        if (action == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(ScanActivity.this)
            .setMessage("真的退出练习?")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ScanActivity.this, "退出成功，即将返回主界面", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ScanActivity.this,MainActivity.class));
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