package bwie.com.zhangzhen20171219.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import bwie.com.zhangzhen20171219.bean.JsonBean;
import bwie.com.zhangzhen20171219.model.CallBack;
import bwie.com.zhangzhen20171219.util.HttpsUrl;
import bwie.com.zhangzhen20171219.view.INewsView;

/**
 * Created by dell on 2017/12/19.
 */

public class NewsPresenter {
    private INewsView inv;

    public NewsPresenter(INewsView inv) {
        this.inv = inv;
    }
    public void getNews(int num){
        HashMap<String , String> map = new HashMap<>();
        map.put("source","android");
        map.put("keywords","笔记本");
        map.put("page",num+"");
        HttpsUrl.getInstance().get("http://120.27.23.105/product/searchProducts", map, new CallBack() {
            @Override
            public void onResponse(Object o) {
                JsonBean json = (JsonBean) o;
                if(json!=null){
                    List<JsonBean.DataBean> data = json.getData();
                    Log.i("jjj",data.toString());
                    inv.success(data);
                }
            }

            @Override
            public void onFailure(Exception e) {
                inv.failed(e);
            }
        },JsonBean.class);
    }
    public void detachView(){
        if (inv != null) {
            inv = null;
        }
    }
}