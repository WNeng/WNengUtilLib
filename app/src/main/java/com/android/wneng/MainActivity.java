package com.android.wneng;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.wneng.retrofit.RetrofitHelper;
import com.android.wneng.retrofit.RxRetrofitCache;
import com.android.wneng.retrofit.api.Benefit;
import com.android.wneng.rx.RxHelper;
import com.android.wneng.rx.RxSubscribe;
import com.android.wneng.utils.log.WLog;
import com.android.wneng.utils.toast.ToastHelper;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity {


    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTvContent = (TextView) findViewById(R.id.tv_content);

        Observable<ArrayList<Benefit>> observable = RetrofitHelper.getApiService().rxBenefits(10, 1)
                .compose(RxHelper.<ArrayList<Benefit>>handleResult());

        RxRetrofitCache.load(this, "HttpCache", 10* 60 * 60, observable, false)
                .subscribe(new RxSubscribe<ArrayList<Benefit>>(this, "正在下载福利...") {
                    @Override
                    protected void _onNext(ArrayList<Benefit> benefits) {

                        WLog.e(benefits);
                        mTvContent.setText("获得的结果为：" + benefits.toString());
                    }

                    @Override
                    protected void _onError(String message) {

                        ToastHelper.showToast(message);
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
