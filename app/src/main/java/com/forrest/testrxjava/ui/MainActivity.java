package com.forrest.testrxjava.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.operation.CreateOperation;
import com.forrest.testrxjava.operation.FromOperation;
import com.forrest.testrxjava.operation.IOperation;
import com.forrest.testrxjava.operation.JustOperation;
import com.forrest.testrxjava.operation.MapOperation;
import com.forrest.testrxjava.operation.ScanOperation;
import com.forrest.testrxjava.operation.SubscribeOperation;
import com.forrest.testrxjava.view.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.id_recyclerview) RecyclerView recyclerView;

    private String[] operationArray={"create","just","from","map","Subscribe","scan"};
    private IOperation[] operations=new IOperation[operationArray.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        operations[0]=new CreateOperation(getWindow().getDecorView());
        operations[1]=new JustOperation();
        operations[2]=new FromOperation();
        operations[3]=new MapOperation();
        operations[4]=new SubscribeOperation(getWindow().getDecorView());
        operations[5]=new ScanOperation();


    }

    private void initView(){
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(new MainAdapter());
    }



    @Override
    public void onClick(final View v) {
        if(v.getTag() instanceof Integer){
            new Thread(){

                @Override
                public void run() {
                    operations[(int) v.getTag()].exeCute();
                }
            }.start();
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{

        private LayoutInflater inflater;

        public MainAdapter(){
            this.inflater=LayoutInflater. from(MainActivity.this);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout. item_main,parent, false);
            MyViewHolder holder= new MyViewHolder(view,MainActivity.this);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setTag(position);
            holder.tv.setText(operationArray[position]);
        }

        @Override
        public int getItemCount() {
            return operationArray.length;
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



}
