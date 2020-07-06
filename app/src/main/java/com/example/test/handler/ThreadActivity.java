package com.example.test.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.test.R;

public class ThreadActivity extends AppCompatActivity {
    private Handler handler1;
    private Handler handler2;
    private Handler handler3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initHandler1();
        initHandler2();
        initHandler3();
    }

    /**
     * 初始化handler（主线程）
     */
    private void initHandler1() {
        handler1 = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == 1) {
                    Log.d("Test", ">>>>>>>>>>>>>>>>>Main " + Thread.currentThread());
                    Toast.makeText(ThreadActivity.this,"hanlder1",Toast.LENGTH_SHORT).show();
                }

                if (msg.what == 1) {
                    Log.d("Test", ">>>>>>>>>>>>>>>>>Main1 " + Thread.currentThread());
                    Toast.makeText(ThreadActivity.this,"hanlder11",Toast.LENGTH_SHORT).show();
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Test", ">>>>>>>>>>>>>>>>> " + Thread.currentThread());
                Message message = handler1.obtainMessage(); //最好使用Handler.obtainMessage() ，直接new Message会带来内存的消耗
                message.arg1 = 1;
                handler1.sendMessage(message);
            }
        }).start();
    }

    /**
     * 初始化handler（子线程）
     */
    private void initHandler2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler2 = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.arg1 == 1) {
                            Toast.makeText(ThreadActivity.this,"hanlder2",Toast.LENGTH_SHORT).show();
                        }
                        super.handleMessage(msg);
                    }
                };
                Message message = handler2.obtainMessage();
                message.arg1 = 1;
                handler2.sendMessage(message);
                Looper.loop();
            }
        }).start();
    }

    /**
     * 第三方库初始化handler（子线程）
     */
    private void initHandler3() {
        HandlerThread thread = new HandlerThread("Test");
        thread.start();
        handler3 = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (msg.arg1 == 1) {
                    Log.d("Test", ">>>>>>>>>>>>>>>>>Test " + Thread.currentThread());
                    Toast.makeText(ThreadActivity.this,"hanlder3",Toast.LENGTH_SHORT).show();
                    handler1.sendEmptyMessage(1);
                }
                super.handleMessage(msg);
            }
        };
        Message message = handler3.obtainMessage();
        message.arg1 = 1;
        handler3.sendMessageDelayed(message, 2000);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ThreadActivity.class);
        context.startActivity(intent);
    }
}
