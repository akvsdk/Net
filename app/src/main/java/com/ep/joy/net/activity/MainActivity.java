package com.ep.joy.net.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.GlideProxy;
import com.ep.joy.net.subscribers.ProgressSubscriber;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";

    private List<Students> studentsList = new ArrayList<>();
    private Subscriber<String> sub;
    private int i=0;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (TextView) findViewById(R.id.tv);
        mImageView = (ImageView) findViewById(R.id.img);
        setData();
        sub = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                JLog.e(s);
            }
        };


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

    private void setData() {
        Students.Clas[] clz = new Students.Clas[3];

        for (int i = 0; i < 10; i++) {
            Students students = new Students(i, "a" + i, clz);
            studentsList.add(students);
        }

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
                // Random random = new Random();
                // int i = random.nextInt(10);
                mView.setText(result.get(i).getTitle());
                String mTest = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(MainActivity.this, baseUrl + result.get(i).getImg(), mImageView);

            }
        }, 4);
    }

    class Students {
        private int id;
        private String name;
        private Clas[] clas;

        public Students(int id, String name, Clas[] clas) {
            this.id = id;
            this.name = name;
            this.clas = clas;
        }

        public class Clas {
            public String clas;

            public Clas(String clas) {
                this.clas = clas;
            }
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Clas[] getClas() {
            return clas;
        }

        public void setClas(Clas[] clas) {
            this.clas = clas;
        }
    }
}
