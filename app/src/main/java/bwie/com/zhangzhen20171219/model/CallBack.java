package bwie.com.zhangzhen20171219.model;

/**
 * Created by dell on 2017/12/19.
 */

public interface CallBack {
    void onResponse(Object o);
    void onFailure(Exception e);
}