package com.ep.joy.net.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ep.joy.net.R;
import com.ep.joy.net.base.BaseFragment;
import com.ep.joy.net.bean.New;
import com.ep.joy.net.bean.Q;
import com.ep.joy.net.utils.Events;
import com.ep.joy.net.utils.RxBus;
import com.google.gson.Gson;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

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
public class FragmentTwo extends BaseFragment {


    private static final String ARGS_INSTANCE = FragmentTwo.class.getSimpleName();
    int mInt;
    private Gson gson;
    private String s;
    TextView tv;

    public static FragmentTwo newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FragmentTwo fragment = new FragmentTwo();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_two_layout;
    }

    @Override
    protected void initView(View view) {
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
        tv = (TextView) view.findViewById(R.id.tv_two);
    }

    @Override
    protected void initData() {
        RxBus.with(this).setEvent(Events.DO)
                .setEndEvent(FragmentEvent.DESTROY_VIEW)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        List<New> q = events.getContent();
                        tv.setText(q.get(0).getTitle());
                    }
                }).create();


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Q q = new Q("HHHHHHHHH", 25, false);
                RxBus.getInstance().send(Events.SEND4, q);
            }
        });
    }


}
