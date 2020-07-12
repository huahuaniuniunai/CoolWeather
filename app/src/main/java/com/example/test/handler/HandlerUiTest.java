package com.example.test.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.R;

/**
 * 在子线程中更新UI
 */
public class HandlerUiTest extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    public static final int UPDATE_TEXT = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == UPDATE_TEXT) {
                textView.setText("Nice to meet you");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_ui_test);
        textView = findViewById(R.id.handler_text);
        Button changeText = findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.arg1 = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            }
            default:
                break;
        }
    }
}
