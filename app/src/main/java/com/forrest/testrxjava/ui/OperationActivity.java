package com.forrest.testrxjava.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.adapter.MainAdapter;
import com.forrest.testrxjava.operation.BufferOperation;
import com.forrest.testrxjava.operation.CreateOperation;
import com.forrest.testrxjava.operation.DebounceOperation;
import com.forrest.testrxjava.operation.DeferOperation;
import com.forrest.testrxjava.operation.DistinctOperation;
import com.forrest.testrxjava.operation.FlagMapOperation;
import com.forrest.testrxjava.operation.FromOperation;
import com.forrest.testrxjava.operation.GroupByOperation;
import com.forrest.testrxjava.operation.IOperation;
import com.forrest.testrxjava.operation.IntervalOperation;
import com.forrest.testrxjava.operation.JustOperation;
import com.forrest.testrxjava.operation.MapOperation;
import com.forrest.testrxjava.operation.RangeOperation;
import com.forrest.testrxjava.operation.RepeatOperation;
import com.forrest.testrxjava.operation.RetryOperation;
import com.forrest.testrxjava.operation.ScanOperation;
import com.forrest.testrxjava.operation.SubscribeOperation;
import com.forrest.testrxjava.operation.TakeOperation;
import com.forrest.testrxjava.operation.WindowOperation;
import com.forrest.testrxjava.operation.ZipOperation;
import com.forrest.testrxjava.view.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OperationActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.id_recyclerview) RecyclerView recyclerView;

    private String[] operationArray={"create","just","from","map","Subscribe","scan","retry","Debounce",
            "IntervalOperation","DeferOperation","RangeOperation","RepeatOperation",
            "BufferOperation","flagmap","GroupByOperation","TakeOperation","WindowOperation",
            "DistinctOperation","ZipOperation"};
    private IOperation[] operations=new IOperation[operationArray.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        initView();

        operations[0]=new CreateOperation();
        operations[1]=new JustOperation();
        operations[2]=new FromOperation();
        operations[3]=new MapOperation();
        operations[4]=new SubscribeOperation(getWindow().getDecorView());
        operations[5]=new ScanOperation();
        operations[6]=new RetryOperation();
        operations[7]=new DebounceOperation();
        operations[8]=new IntervalOperation();
        operations[9]=new DeferOperation();
        operations[10]=new RangeOperation();
        operations[11]=new RepeatOperation();
        operations[12]=new BufferOperation();
        operations[13]=new FlagMapOperation();
        operations[14]=new GroupByOperation();
        operations[15]=new TakeOperation();
        operations[16]=new WindowOperation();
        operations[17]=new DistinctOperation();
        operations[18]=new ZipOperation();


    }

    private void initView(){
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(new MainAdapter(this,this,operationArray));
    }



    @Override
    public void onClick(final View v) {
        if(v.getTag() instanceof Integer){
          operations[(int) v.getTag()].exeCute();
        }
    }

//    private class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.MyViewHolder>{
//
//        private LayoutInflater inflater;
//
//        public OperationAdapter(){
//            this.inflater=LayoutInflater. from(OperationActivity.this);
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout. item_main,parent, false);
//            MyViewHolder holder= new MyViewHolder(view,OperationActivity.this);
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            holder.tv.setTag(position);
//            holder.tv.setText(operationArray[position]);
//        }
//
//        @Override
//        public int getItemCount() {
//            return operationArray.length;
//        }
//
//        class MyViewHolder extends RecyclerView.ViewHolder {
//
//            TextView tv;
//            public MyViewHolder(View view,View.OnClickListener onClickListener) {
//                super(view);
//                tv=(TextView) view.findViewById(R.id. tv_text);
//                tv.setOnClickListener(onClickListener);
//            }
//
//        }
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
