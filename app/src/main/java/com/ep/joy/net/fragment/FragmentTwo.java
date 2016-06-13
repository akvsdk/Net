package com.ep.joy.net.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.jiongbull.jlog.JLog;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
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
public class FragmentTwo extends Fragment {


    private static final String ARGS_INSTANCE = FragmentTwo.class.getSimpleName();
    int mInt;
    private Subscriber<Long> sub;

    public static FragmentTwo newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FragmentTwo fragment = new FragmentTwo();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_layout, container, false);
        final TextView tv = (TextView) view.findViewById(R.id.frg2_tv);
        sub = new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if (aLong < 5) {
                    JLog.e(aLong + "====");
                } else {
                    tv.setText("END");
                }
            }
        };

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
                        .flatMap(new Func1<Integer, Observable<String>>() {
                            @Override
                            public Observable<String> call(Integer integer) {
                                return Observable.just("flat map:" + integer);
                            }
                        })
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                JLog.w(s);
                            }
                        });


                Observable.just(1, 2, 3, 4, 5, 6, 7, 9, 0, 3)
                        .last(new Func1<Integer, Boolean>() {
                            @Override
                            public Boolean call(Integer integer) {
                                return integer < 5;
                            }
                        })
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                JLog.e(integer + "@@@@@@");
                            }
                        })
                ;

//                if (tv.getText().equals("END"))
//                    sub.unsubscribe();
//
//
//                Observable.interval(1, TimeUnit.SECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(sub);


//                DeferObserver()
//                        .subscribe(new Action1<Long>() {
//                            @Override
//                            public void call(Long aLong) {
//                                JLog.e("defer------" + aLong);
//                            }
//                        });
//
//                JustObserver()
//                        .subscribe(new Action1<Long>() {
//                    @Override
//                    public void call(Long aLong) {
//                        JLog.e("just------" + aLong);
//                    }
//                });

            }
        });
        //   Rxtest();
        return view;
    }


    private Observable<Long> DeferObserver() {
        return Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return Observable.just(System.currentTimeMillis());
            }
        });
    }

    private Observable<Long> JustObserver() {
        return Observable.just(System.currentTimeMillis());
    }


    private void Rxtest() {
        Observable.range(10, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                JLog.e(integer + "------");
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


}
