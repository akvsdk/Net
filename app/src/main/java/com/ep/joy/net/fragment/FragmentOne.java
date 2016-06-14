package com.ep.joy.net.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.base.BaseFragment;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.bean.Q;
import com.ep.joy.net.bean.TG;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.GlideProxy;
import com.ep.joy.net.http.MyBaseCallBack;
import com.ep.joy.net.service.Factory;
import com.ep.joy.net.subscribers.ProgressSubscriber;
import com.ep.joy.net.utils.Events;
import com.ep.joy.net.utils.RxBus;
import com.ep.joy.net.utils.RxUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

public class FragmentOne extends BaseFragment {

    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";
    private static final String ARGS_INSTANCE = FragmentOne.class.getSimpleName();
    int mInt;
    private Button btn;
    private int i = 0;
    private List<New> bean = new ArrayList<>();

    public static FragmentOne newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_one_layout;
    }

    @Override
    protected void initView(View view) {
        mView = (TextView) view.findViewById(R.id.mztv);
        mImageView = (ImageView) view.findViewById(R.id.img);
        btn = (Button) view.findViewById(R.id.next);
        //test();
        //test2();
        Q q = new Q("Joy", 18, true);
        RxBus.getInstance().send(Events.DO, q);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                mView.setText(bean.get(i).getTitle());
                String mTest = baseUrl + bean.get(i).getImg();
                GlideProxy.getInstance().loadImage(getActivity(), mTest, mImageView);
            }
        });
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "4");
        map.put("row", "10");
        Observable observable = Factory.provideImgService().getImg(map).map(new RxUtils.HttpResultFunc<List<New>>());
        Subscriber subscriber = new ProgressSubscriber<List<New>>(getActivity()) {
            @Override
            protected void onSuccess(List<New> result) {
                bean.addAll(result);
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                mView.setText(bean.get(i).getTitle());
                String mTest = baseUrl + bean.get(i).getImg();
                GlideProxy.getInstance().loadImage(getActivity(), mTest, mImageView);
            }

        };
        addSubscription(observable, subscriber);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
    }


    private void test() {
        AppDao.getInstance().getRxImg(new ProgressSubscriber<List<New>>(getActivity()) {
            @Override
            protected void onSuccess(List<New> result) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                mView.setText(result.get(i).getTitle());
                String mTest = baseUrl + result.get(i).getImg();
                GlideProxy.getInstance().loadImage(getActivity(), mTest, mImageView);

            }
        }, 4);
    }

    private void test2() {
        AppDao.getInstance().getimg(new MyBaseCallBack<TG>() {
            @Override
            protected void onSuccess(TG result) {
                if (i < 19) {
                    i++;
                } else {
                    i = 0;
                }
                mView.setText(result.getTngou().get(i).getTitle());
                String mTest = baseUrl + result.getTngou().get(i).getImg();
                GlideProxy.getInstance().loadImage(getActivity(), mTest, mImageView);

            }
        });
    }


}
