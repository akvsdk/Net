package com.ep.joy.net.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.activity.MainTabActivity;
import com.ep.joy.net.base.BaseFragment;
import com.ep.joy.net.bean.Q;
import com.ep.joy.net.utils.Events;
import com.ep.joy.net.utils.RxBus;
import com.jiongbull.jlog.JLog;

import rx.functions.Action1;

/**
 * 类描述:
 * 创建人:lin.ma@renren-inc.com
 * 创建时间:2016 16-4-8 18:34
 * 备注:{@link }
 * 修改人:
 * 修改时间:
 * 修改备注:
 */
public class FragmentFour extends BaseFragment {


    private static final String ARGS_INSTANCE = FragmentFour.class.getSimpleName();
    int mInt;
    TextView tv;

    public static FragmentFour newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FragmentFour fragment = new FragmentFour();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_four_layout;
    }

    @Override
    protected void initView(View view) {
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
        tv = (TextView) view.findViewById(R.id.tv_four);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainTabActivity) getActivity()).setItem(0,"呵呵");
            }
        });

    }

    @Override
    protected void initData() {
        RxBus.with(this)
                .setEvent(Events.SEND4)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        Q q1 = events.getContent();
                        tv.setText(q1.toString());
                    }
                })
                .onError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        JLog.e(throwable.getMessage());
                    }
                })
                .create();
    }

}
