package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.test.recyclerview.Fruit;
import com.example.test.recyclerview.RecyclerViewDemo;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        initFruit();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewDemo adapter = new RecyclerViewDemo(fruitList);
        recyclerView.setAdapter(adapter);
    }

    public void initFruit() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(R.drawable.apple_pic, "apple");
            fruitList.add(apple);
            Fruit banana = new Fruit(R.drawable.banana_pic, "banana");
            fruitList.add(banana);
            Fruit orange = new Fruit(R.drawable.orange_pic, "orange");
            fruitList.add(orange);
            Fruit cherry = new Fruit(R.drawable.cherry_pic, "cherry");
            fruitList.add(cherry);
            Fruit grape = new Fruit(R.drawable.grape_pic, "grape");
            fruitList.add(grape);
            Fruit mango = new Fruit(R.drawable.mango_pic, "mango");
            fruitList.add(mango);
            Fruit pear = new Fruit(R.drawable.pear_pic, "pear");
            fruitList.add(pear);
            Fruit watermelon = new Fruit(R.drawable.watermelon_pic, "watermelon");
            fruitList.add(watermelon);
            Fruit pineapple = new Fruit(R.drawable.pineapple_pic, "pineapple");
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(R.drawable.strawberry_pic, "strawberry");
            fruitList.add(strawberry);
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FourthActivity.class);
        context.startActivity(intent);
    }
}
