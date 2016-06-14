package com.ep.joy.net.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ep.joy.net.R;
import com.ep.joy.net.bean.Q;
import com.google.gson.Gson;
import com.jiongbull.jlog.JLog;


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
    private Gson gson;
    private String s;

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
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         gson =new Gson();
        Q q = new Q("Joy",18,true);
         s= gson.toJson(q);
        JLog.e(s);
        Q q1=gson.fromJson(s,Q.class);
        JLog.e(q1.toString());
    }

}
