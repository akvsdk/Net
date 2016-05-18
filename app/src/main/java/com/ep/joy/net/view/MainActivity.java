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
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";
    int i = 0;
    String mTest;
    private List<Students> studentsList = new ArrayList<>();
    private Subscriber<String> sub;

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
        //  test();

        rx();


    }

    private void rx() {

        //     Observable.just("abc", "123").subscribe(sub);

//        Observable.from(studentsList).subscribe(new Action1<Students>() {
//            @Override
//            public void call(Students students) {
//                JLog.e(students.getId() + "");
//            }
//        });

//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("123");
//                subscriber.onNext("456");
//            }
//        }).subscribe(sub);

//        Observable.from(studentsList).map(new Func1<Students, String>() {
//            @Override
//            public String call(Students students) {
//                return students.getClas()[3];
//            }
//        })
//                .subscribe(sub);
        Observable.from(studentsList).flatMap(new Func1<Students, Observable<Students.Clas>>() {
            @Override
            public Observable<Students.Clas> call(Students students) {
                return Observable.from(students.clas);
            }
        })
                .subscribe(new Subscriber<Students.Clas>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Students.Clas clas) {
                        JLog.e("onNext " + clas);
                    }
                });


    }

    private void setData() {
        Students.Clas[] clz = new Students.Clas[3];

        for (int i = 0; i < 10; i++) {
            Students students = new Students(i, "a" + i, clz);
            studentsList.add(students);
        }

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
