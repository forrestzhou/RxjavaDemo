package com.forrest.testrxjava.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.forrest.testrxjava.R;
import com.forrest.testrxjava.adapter.MainAdapter;
import com.forrest.testrxjava.view.DividerItemDecoration;
import butterknife.BindView;


public class MainActivity extends BaseRxjavaActivity implements View.OnClickListener{

    @BindView(R.id.id_recyclerview) RecyclerView recyclerView;

    private String[] operationArray={"操作符","子线程耗时,主线程更新ui","防止误操作"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
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
                case 1:
                    intent =new Intent(this,Demo1Activity.class);
                    break;
                case 2:
                    intent =new Intent(this,Demo2Activity.class);
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
