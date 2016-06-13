package com.ep.joy.net.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


/**
 * 类描述:
 * 创建人:lin.ma@renren-inc.com
 * 创建时间:2016 16-4-8 18:34
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class FragmentOne extends Fragment {

    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";

    private List<Students> studentsList = new ArrayList<>();
    private int i=0;
    private static final String ARGS_INSTANCE = FragmentOne.class.getSimpleName();
    int mInt;
    private Button btn;

    public static FragmentOne newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = (TextView)view.findViewById(R.id.tv);
        mImageView = (ImageView) view.findViewById(R.id.img);
        btn = (Button) view.findViewById(R.id.next);
        setData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
    }

    private void setData() {
        Students.Clas[] clz = new Students.Clas[3];

        for (int i = 0; i < 10; i++) {
            Students students = new Students(i, "a" + i, clz);
            studentsList.add(students);
        }

    }

    private void test() {
        AppDao.getInstance().fuck(new ProgressSubscriber<List<New>>(getActivity()) {
            @Override
            protected void onSuccess(List<New> result) {
                result.get(0).getCount();


                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                // Random random = new Random();
                // int i = random.nextInt(10);
                mView.setText(result.get(i).getTitle());
                String mTest = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(getActivity(), baseUrl + result.get(i).getImg(), mImageView);

            }
        }, 4);
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

        Observable.from(studentsList).map(new Func1<Students, String>() {
            @Override
            public String call(Students students) {
                return students.getName();
            }
        })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !s.endsWith("0");
                    }
                })
                .subscribe(new Subscriber<String>() {
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
                });
//        Observable.from(studentsList).flatMap(new Func1<Students, Observable<Students.Clas>>() {
//            @Override
//            public Observable<Students.Clas> call(Students students) {
//                return Observable.from(students.clas);
//            }
//        })
//                .filter(new Func1<Students.Clas, Boolean>() {
//                    @Override
//                    public Boolean call(Students.Clas clas) {
//                        return clas!=null;
//                    }
//                })
//                .subscribe(new Subscriber<Students.Clas>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Students.Clas clas) {
//                        JLog.e("onNext " + clas);
//                    }
//                });


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
