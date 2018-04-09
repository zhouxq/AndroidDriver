package com.example.slope.androiddriver.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zhou on 2018/4/9.
 */
@Table(name = "options")
public class Options {
    @Column(name = "ID",isId = true,autoGen = true)
    private int id;

    @Column(name = "describe")//NAME字段非空
    private String describe;

    @Column(name = "type")
    private int type;

    public int getId() {
        return id;
    }

    public Options setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescribe() {
        return describe;
    }

    public Options setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public int getType() {
        return type;
    }

    public Options setType(int type) {
        this.type = type;
        return this;
    }
}
