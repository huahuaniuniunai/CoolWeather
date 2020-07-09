package com.example.test.litePal;

import org.litepal.crud.LitePalSupport;

/**
 * 新建表：就相当于新建java bean，并在litepal.xml中list下添加完整包名，新增表则版本号也变。
 * 向表添加一列，直接在javabean中添加一个字段且设置getter/setter。
 */
public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }
}
