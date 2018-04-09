package com.example.slope.androiddriver.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by zhou on 2018/4/9.
 */
@Table(name = "subject")
public class Subject {
    @Column(name = "ID",isId = true,autoGen = true)
    private int id;

    @Column(name = "title",property = "NOT NULL")//NAME字段非空
    private String title;

    @Column(name = "describe")//NAME字段非空
    private String describe;

    @Column(name = "type")
    private int type;

    @Column(name = "answer")
    private int answer;

    @Column(name = "image")
    private String image;

    @Column(name = "optionsId")
    private int optionsId;

    @Column(name = "favorites")//收藏夹
    private int favorites;



    public int getId() {
        return id;
    }

    public Subject setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Subject setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescribe() {
        return describe;
    }

    public Subject setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    public int getType() {
        return type;
    }

    public Subject setType(int type) {
        this.type = type;
        return this;
    }

    public int getAnswer() {
        return answer;
    }

    public Subject setAnswer(int answer) {
        this.answer = answer;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Subject setImage(String image) {
        this.image = image;
        return this;
    }

    public int getOptionsId() {
        return optionsId;
    }

    public Subject setOptionsId(int optionsId) {
        this.optionsId = optionsId;
        return this;
    }

    public int getFavorites() {
        return favorites;
    }

    public Subject setFavorites(int favorites) {
        this.favorites = favorites;
        return this;
    }
}
