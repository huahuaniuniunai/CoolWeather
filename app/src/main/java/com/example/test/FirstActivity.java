package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Intent intent = getIntent();
        String data1 = intent.getExtras().getString("param1");
        int data2 = intent.getExtras().getInt("param2");
        Log.d("demo", data1);
        Log.d("demo", data2+"");
    }

    public static void actionStart(Context context, String data1, int data2) {
        Bundle bundle = new Bundle();
        bundle.putString("param1", data1);
        bundle.putInt("param2", data2);
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
