package com.ep.joy.net.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.bean.Car;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.MyBaseCallBack;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (TextView) findViewById(R.id.tv);
    }

    public void doHttp(View view) {
       getCar();
        //test();

    }

    private void test() {
        AppDao.getInstance().fuck(new MyBaseCallBack<Car>(this) {
            @Override
            protected void onSuccess(Car result) {
                mView.setText(result.getNewsList().get(0).getSubtitle());
            }
        });
    }

    private void getCar() {
        AppDao.getInstance().img(new MyBaseCallBack<List<New>>(this) {
            @Override
            protected void onSuccess(List<New> result) {
                Random random = new Random();
                int i = random.nextInt(10);
                mView.setText(result.get(i).getTitle());
            }
        });
    }
}
