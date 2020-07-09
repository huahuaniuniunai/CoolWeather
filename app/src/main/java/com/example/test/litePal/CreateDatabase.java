package com.example.test.litePal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.MyApplication;
import com.example.test.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

// https://github.com/guolindev/LitePal
public class CreateDatabase extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "CreateDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_database);

        // 新建数据库
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(this);

        // 增：添加数据
        Button create = findViewById(R.id.create);
        create.setOnClickListener(this);

        // 删：删除数据
        Button delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);

        // 改：更新数据
        Button update = findViewById(R.id.update);
        update.setOnClickListener(this);

        // 查：查询数据
        Button retrieve = findViewById(R.id.retrieve);
        retrieve.setOnClickListener(this);

    }

    public static void actionStart() {
        Intent intent = new Intent(MyApplication.getContext(), CreateDatabase.class);
        MyApplication.getContext().startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                LitePal.getDatabase();
                break;

            case R.id.create: {
                Book bookCreate = new Book();
                bookCreate.setName("The Da Vinci Code");
                bookCreate.setAuthor("Dan Brown");
                bookCreate.setPages(454);
                bookCreate.setPrice(16.96);
                bookCreate.save();
                break;
            }

            case R.id.delete:
                LitePal.deleteAll(Book.class, "price < ?", "15");
                break;

            case R.id.update: {
                Book bookUpdate = new Book();
                bookUpdate.setPrice(14.95);
                bookUpdate.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
                break;
            }

            case R.id.retrieve: {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    Log.d("TAG", book.getName());
                    Log.d("TAG", book.getAuthor());
                    Log.d("TAG", book.getPages() + "");
                    Log.d("TAG", book.getPrice() + "");
                }
                break;
            }
        }
    }
}
