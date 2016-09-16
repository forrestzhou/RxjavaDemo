package com.forrest.testrxjava.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forrest.testrxjava.R;

/**
 * Created by forrest on 16/9/16.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;
    private String[] arrays;
    private View.OnClickListener onClickListener;

    public MainAdapter(Context context,View.OnClickListener onClickListener,String[] arrays){
        this.context=context;
        this.onClickListener=onClickListener;
        this.inflater=LayoutInflater. from(context);
        this.arrays=arrays;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout. item_main,parent, false);
        MyViewHolder holder= new MyViewHolder(view,onClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setTag(position);
        holder.tv.setText(arrays[position]);
    }

    @Override
    public int getItemCount() {
        if(arrays==null){
            return 0;
        }
        return arrays.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        public MyViewHolder(View view,View.OnClickListener onClickListener) {
            super(view);
            tv=(TextView) view.findViewById(R.id. tv_text);
            tv.setOnClickListener(onClickListener);
        }

    }

}