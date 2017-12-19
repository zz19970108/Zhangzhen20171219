package bwie.com.zhangzhen20171219.util;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import bwie.com.zhangzhen20171219.model.CallBack;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/12/19.
 */

public class HttpsUrl {
    private Handler handler = new Handler();
    private static volatile HttpsUrl instance;
    public HttpsUrl(){

    }
    public static HttpsUrl getInstance(){
        if(null == instance){
            synchronized (HttpsUrl.class){
                if(instance==null){
                    instance = new HttpsUrl();
                }
            }
        }
        return instance;
    }
    public void get(String url, Map<String , String> map, final CallBack callback, final Class cls){
        if(TextUtils.isEmpty(url)){
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        if(url.contains("?")){
            if (url.indexOf("?")==url.length()-1) {

            }else{
                sb.append("&");
            }
        }else{
            sb.append("?");
        }
        for(Map.Entry<String ,String > entry:map.entrySet()){
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        if(sb.indexOf("&") != -1){
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(sb.toString())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                Log.i("ddd",s);
                handler.post(new Runnable() {
                    private Object o;
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(s)){
                            o = null;
                        }else{
                            o = GsonUtils.getInstance().fromJson(s,cls);
                        }
                        callback.onResponse(o);
                    }
                });
            }
        });
    }
}