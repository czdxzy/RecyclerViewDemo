package com.example.myweatherdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myweatherdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2015/9/22.
 */
public class ChooseAreaAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<String> mDatas;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public interface OnItemClickListener{
         void onItemClick(TextView textView, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
    public ChooseAreaAdapter(Context context,List<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_area,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(mDatas.get(position));
        if (mOnItemClickListener !=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.textView,layoutPosition);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}
