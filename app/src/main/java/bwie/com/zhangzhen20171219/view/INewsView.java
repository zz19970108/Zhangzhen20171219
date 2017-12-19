package bwie.com.zhangzhen20171219.view;

import java.util.List;

import bwie.com.zhangzhen20171219.bean.JsonBean;

/**
 * Created by dell on 2017/12/19.
 */

public interface INewsView {
    void failed(Exception e);

    void success(List<JsonBean.DataBean> data);
}