package com.example.test.layoutInflater;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.test.R;

public class TitleLayout extends LinearLayout implements View.OnClickListener {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /**
         * 动态加载布局
         * 通过LayoutInflater的from()方法构建一个LayoutInflater，再调用inflate（）方法动态加载一个布局。
         * 第一个参数是需要加载的布局，第二个参数是给加载好的布局再添加一个父布局
         */
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button button_back = findViewById(R.id.title_back);
        button_back.setOnClickListener(this);

        Button button_edit = findViewById(R.id.title_edit);
        button_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;

            case R.id.title_edit:
                Toast.makeText(getContext(), "You clicked edit button", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
