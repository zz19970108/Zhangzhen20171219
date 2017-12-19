package bwie.com.zhangzhen20171219.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.zhangzhen20171219.R;
import bwie.com.zhangzhen20171219.bean.JsonBean;

/**
 * Created by dell on 2017/12/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<JsonBean.DataBean> list;
    private View v;

    public MyAdapter(Context context, List<JsonBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = View.inflate(context, R.layout.item, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String img = list.get(position).getImages();
        if(img.contains("|")){
            img = img.substring(0, img.indexOf("|"));
        }
        Log.i("imggg",img);
        Glide.with(context).load(img).into(holder.t_img);
        holder.zjg.setText("折扣价:"+list.get(position).getSalenum());
        holder.jg.setText("原价:"+list.get(position).getPrice());
        holder.title.setText(""+list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView t_img;
        private final TextView jg;
        private final TextView title;
        private final TextView zjg;

        public ViewHolder(View itemView) {
            super(itemView);
            t_img = v.findViewById(R.id.iv_timgg);
            jg = v.findViewById(R.id.tv_tjiage);
            zjg = v.findViewById(R.id.tv_zjiage);
            title = v.findViewById(R.id.tv_ttitle);
            jg.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}