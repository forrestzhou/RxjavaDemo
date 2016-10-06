package com.forrest.testrxjava.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.forrest.testrxjava.R;
import com.forrest.testrxjava.adapter.MainAdapter;
import com.forrest.testrxjava.view.DividerItemDecoration;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.orhanobut.logger.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Demo2Activity extends BaseRxjavaActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.edittext)
    EditText editText;
    private List<String> searchDatas =new ArrayList<>();
    private List<String> baseDatas =new ArrayList<>();
    private Random random=new Random();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        initData();
        bindEdittext();
        myAdapter=new MyAdapter(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(
//                this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);

    }

    private void initData(){
        subscription= Observable.create(new Observable.OnSubscribe<List<String>>() {


            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                for (int i=0;i<100;i++){
                    baseDatas.add(String.valueOf(random.nextInt(1000)));
                }
                subscriber.onNext(baseDatas);

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> stringList) {
//                Logger.i(stringList.toString());
//                updateData(stringList);
            }
        });
    }

    private void bindEdittext(){
        RxTextView.textChanges(editText).debounce(500, TimeUnit.MILLISECONDS).observeOn(Schedulers.io()).map(new Func1<CharSequence, List<String>>() {
            @Override
            public List<String> call(CharSequence charSequence) {
                try {

                    if(TextUtils.isEmpty(charSequence)){
                        return null;
                    }
                    List<String> stringList =new ArrayList<String>();
                    String key=charSequence.toString();

                    for (String num: baseDatas){
                        if(num.contains(key)){
                            stringList.add(num);
                        }
                    }
                    return stringList;
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {
                updateData(list);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Logger.e(Log.getStackTraceString(throwable));
            }
        });
    }

    private void updateData(List<String> stringList){
        searchDatas.clear();
        if(stringList!=null){
            searchDatas.addAll(stringList);
        }
        myAdapter.notifyDataSetChanged();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private LayoutInflater inflater;
        private Context context;

        public MyAdapter(Context context){
            this.context=context;
            this.inflater=LayoutInflater. from(context);
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout. item_demo2,parent, false);
            MyAdapter.MyViewHolder holder= new MyAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            holder.tv.setTag(position);
            holder.tv.setText(searchDatas.get(position));
        }

        @Override
        public int getItemCount() {
            if(searchDatas==null){
                return 0;
            }
            return searchDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;
            public MyViewHolder(View view) {
                super(view);
                tv=(TextView) view.findViewById(R.id.tv_text);
            }

        }
    }


}
