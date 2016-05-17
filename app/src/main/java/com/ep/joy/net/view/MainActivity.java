package com.ep.joy.net.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.GlideProxy;
import com.ep.joy.net.http.MyBaseCallBack;
import com.ep.joy.net.subscribers.ProgressSubscriber;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";
    int i = 0;
    String mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (TextView) findViewById(R.id.tv);
        mImageView = (ImageView) findViewById(R.id.img);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("test", mTest);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        GlideProxy.getInstance().loadImage(MainActivity.this, savedInstanceState.getString("test"), mImageView);
    }

    public void doHttp(View view) {
        // getCar();
        test();

    }

    private void test() {
        AppDao.getInstance().fuck(new ProgressSubscriber<List<New>>(MainActivity.this) {
            @Override
            protected void onSuccess(List<New> result) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                // Random random = new Random();
                // int i = random.nextInt(10);
                mView.setText(result.get(i).getTitle());
                mTest = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(MainActivity.this, baseUrl + result.get(i).getImg(), mImageView);

            }
        }, 4);
    }

    private void getCar() {

        AppDao.getInstance().img(new MyBaseCallBack<List<New>>(this) {
            @Override
            protected void onSuccess(List<New> result) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                // Random random = new Random();
                // int i = random.nextInt(10);
                mView.setText(result.get(i).getTitle());
                mTest = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(MainActivity.this, baseUrl + result.get(i).getImg(), mImageView);
            }
        });
    }
}
