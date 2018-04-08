package com.example.slope.androiddriver;
import java.util.List;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.slope.androiddriver.adapter.ScanViewAdapter;
import com.example.slope.androiddriver.drawer.ScanView;

/**
 * Created by zhou on 2018/4/8.
 */

public class ScanActivity extends Activity
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
        for (int i = 0; i < 8; i++)
            items.add("ตฺ " + (i + 1) + " าณ");
        adapter = new ScanViewAdapter(this, items);
        scanview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}