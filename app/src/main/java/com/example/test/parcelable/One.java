package com.example.test.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.test.MyApplication;
import com.example.test.R;

public class One extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Person person = new Person();
        person.name = "Tom";
        person.age = 23;
        Intent intent = new Intent(this, Two.class);
        intent.putExtra("person", person);
        startActivity(intent);
    }

    public static void actionStart() {
        Intent intent = new Intent(MyApplication.getContext(), One.class);
        MyApplication.getContext().startActivity(intent);
    }
}
