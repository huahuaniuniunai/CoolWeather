package com.example.test.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.test.R;

public class Two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intent = getIntent();
        Person data = intent.getParcelableExtra("person");
        String name = data.name;
        int age = data.age;
        Log.d("demo", name + age);
    }
}
