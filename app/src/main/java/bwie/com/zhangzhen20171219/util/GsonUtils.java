package bwie.com.zhangzhen20171219.util;

import com.google.gson.Gson;

/**
 * Created by dell on 2017/12/19.
 */

public class GsonUtils {
    private static Gson instance;
    private GsonUtils(){

    }
    public static Gson getInstance(){
        if (instance == null) {
            instance = new Gson();
        }
        return instance;
    }
}