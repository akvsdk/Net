package com.ep.joy.net.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.base.ToolbarActvitiy;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.GlideProxy;
import com.ep.joy.net.service.Factory;
import com.ep.joy.net.subscribers.ProgressSubscriber;
import com.ep.joy.net.utils.RxUtils;

import java.util.HashMap;
import java.util.List;


public class MainActivity extends ToolbarActvitiy {
    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";

    private int i = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        mView = (TextView) findViewById(R.id.mztv);
        mImageView = (ImageView) findViewById(R.id.img);
        getToolbar().setDisplayHomeAsUpEnabled(false).setSubTitle("Home");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        GlideProxy.getInstance().loadImage(MainActivity.this, savedInstanceState.getString("test"), mImageView);
    }

    public void doHttp(View view) {
        // getCar();
        //   test();
        showSnackbar(i + "");
        HashMap<String, String> map = new HashMap<>();
        map.put("id", 5 + "");
        map.put("row", "3");
        addSubscription(Factory.provideImgService().getImg(map)
                        .map(new RxUtils.HttpResultFunc<List<New>>())
                , new ProgressSubscriber<List<New>>(this) {
                    @Override
                    protected void onSuccess(List<New> result) {
                        if (i < 19) {
                            i++;
                        } else {
                            i = 0;
                        }
                        mView.setText(result.get(i).getTitle());
                        String mUrl = baseUrl + result.get(i).getImg();
                        GlideProxy.getInstance().loadImage(MainActivity.this, mUrl, mImageView);
                    }

                });

    }


    private void test() {
        AppDao.getInstance().getRxImg(new ProgressSubscriber<List<New>>(MainActivity.this) {
            @Override
            protected void onSuccess(List<New> result) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                mView.setText(result.get(i).getTitle());
                String mUrl = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(MainActivity.this, mUrl, mImageView);
            }
        }, 4);
    }


}
