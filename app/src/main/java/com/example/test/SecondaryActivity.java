package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.test.fragment.FragmentActivity;
import com.example.test.handler.ThreadActivity;
import com.example.test.news.NewsActivity;
import com.example.test.okHttp.OkhttpActivity;
import com.example.test.parcelable.One;
import com.example.test.recyclerView.FourthActivity;

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        Button button1 = findViewById(R.id.b_1);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.b_2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.b_3);
        button3.setOnClickListener(this);

        Button button4 = findViewById(R.id.b_4);
        button4.setOnClickListener(this);

        Button button5 = findViewById(R.id.b_5);
        button5.setOnClickListener(this);

        Button button6 = findViewById(R.id.b_6);
        button6.setOnClickListener(this);

        Button button7 = findViewById(R.id.b_7);
        button7.setOnClickListener(this);

        Button button8 = findViewById(R.id.b_8);
        button8.setOnClickListener(this);

        Button button9 = findViewById(R.id.b_9);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_1:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SecondaryActivity.this);
                dialog.setTitle("This is dialog");
                dialog.setMessage("something important...");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("FALSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.b_2:
                final ProgressDialog progressDialog = new ProgressDialog(SecondaryActivity.this);
                progressDialog.setTitle("This is progressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },3000);
                break;
            case R.id.b_3:
                ThirdActivity.actionStart(this, "张三三", "男");
                break;
            case R.id.b_4:
                FourthActivity.actionStart(this);
                break;
            case R.id.b_5:
                FragmentActivity.actionStart(this);
                break;
            case R.id.b_6:
                NewsActivity.actionStart(this);
                break;
            case R.id.b_7:
                ThreadActivity.actionStart(this);
                break;
            case R.id.b_8:
                OkhttpActivity.actionStart(this);
                break;
            case R.id.b_9:
                One.actionStart();
                break;
            default:
                break;
        }
    }
}
