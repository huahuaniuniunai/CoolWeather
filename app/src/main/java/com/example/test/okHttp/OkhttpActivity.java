package com.example.test.okHttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// https://github.com/square/okhttp
public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSyn("https://www.javaer.fun/api/v2/video/parse?reqText=不愧是现在最受欢迎的车模，没穿高跟鞋都差不多跟上男模的身高了 https://v.douyin.com/J8FXdDe/ 复制此链接，打开【抖音短视频】，直接观看视频！");

//                getAsyn("https://www.javaer.fun/api/v2/video/parse?reqText=不愧是现在最受欢迎的车模，" +
//                        "没穿高跟鞋都差不多跟上男模的身高了 https://v.douyin.com/J8FXdDe/ 复制此链接，打开【抖音短视频】，直接观看视频！");
            }
        });
    }

    public void getSyn(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();
                    // 创建Request
                    Request request = new Request.Builder()
                            .url(url)// 访问连接
//                            .post(RequestBody.create(MediaType.parse(""), "{}"))
                            .get()// 默认是get请求，可以省略
                            .build();
                    // 创建Call对象
                    Call call = client.newCall(request);
                    // 通过execute()方法获得请求响应的Response对象
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        // 处理网络请求的响应，处理UI需要在UI线程中处理
                        // ...
                        String data =  response.body().string();
                        /**
                         * JSON的JSONObject解析方式
                         */
                        parseJSONWithJSONObject(data);

                        /**
                         * XML的Pull解析方式
                         */
                        parseXMLWithPull(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getAsyn(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()// 默认是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("okhttp", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    Log.d("okhttp", "异步onResponse: " + data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OkhttpActivity.class);
        context.startActivity(intent);
    }

    /**
     * XML 解析
     * @param xmlData
     */
    public void parseXMLWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_DOCUMENT: {
                        if ("app".equals(nodeName)) {
                            Log.d("demo", "id id" + id);
                            Log.d("demo", "name is" + name);
                            Log.d("demo", "version is" + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * JSON 解析
     * @param jsonData
     */
    public void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("demo", "id is" + id);
                Log.d("demo", "name is" + name);
                Log.d("demo", "version is" + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
