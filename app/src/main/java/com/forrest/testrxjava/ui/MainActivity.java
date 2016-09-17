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
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.id_recyclerview) RecyclerView recyclerView;

    private String[] operationArray={"操作符"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
            Intent intent = null;
            switch ((int) v.getTag()){
                case 0:
                  intent =new Intent(this,OperationActivity.class);
                    break;

            }
            startActivity(intent);

        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
