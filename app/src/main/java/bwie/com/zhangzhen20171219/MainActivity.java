package bwie.com.zhangzhen20171219;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.zhangzhen20171219.adapter.MyAdapter;
import bwie.com.zhangzhen20171219.bean.JsonBean;
import bwie.com.zhangzhen20171219.presenter.NewsPresenter;
import bwie.com.zhangzhen20171219.view.INewsView;

public class MainActivity extends AppCompatActivity implements INewsView {

    private List<JsonBean.DataBean> list = new ArrayList<>();
    private XRecyclerView rv;
    private MyAdapter adapter;
    private int num =1;
    private boolean aa=true;
    private boolean flag = true;
    private ImageView img;
    private NewsPresenter presenter;
    private EditText et;
    private Button butss;
    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        presenter = new NewsPresenter(this);
        presenter.getNews(num);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        adapter = new MyAdapter(this, list);
        rv.setAdapter(adapter);
        initLoading();
    }

    private void initLoading() {
        rv.setLoadingMoreEnabled(true);
        rv.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                num=1;
                aa=true;
                presenter.getNews(num);
            }

            @Override
            public void onLoadMore() {
                num++;
                aa=false;
                presenter.getNews(num);
            }
        });
    }

    private void initView() {
        rv = (XRecyclerView) findViewById(R.id.rv);
        img = (ImageView) findViewById(R.id.iv_img);
        et = (EditText) findViewById(R.id.et);
        butss = (Button) findViewById(R.id.but_ss);

        butss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = et.getText().toString();
                Log.i("sss",s);
                if(s.equals("笔记本")){
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                }else if(s.equals("手机")){
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"没有该数据",Toast.LENGTH_LONG).show();
                }
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    img.setImageResource(R.drawable.grid_icon);
                    StaggeredGridLayoutManager managera = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                    rv.setLayoutManager(managera);
                    flag=false;
                } else {
                    img.setImageResource(R.drawable.lv_icon);
                    LinearLayoutManager anager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(anager);
                    flag=true;
                }
            }
        });

    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void success(List<JsonBean.DataBean> data) {
        if (aa) {
            rv.refreshComplete();
        }else {
            rv.loadMoreComplete();
        }
        Log.i("zzz",data.toString());
        if(null!=data){
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }
}
