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
import com.ep.joy.net.bean.TG;
import com.ep.joy.net.http.AppDao;
import com.ep.joy.net.http.GlideProxy;
import com.ep.joy.net.http.MyBaseCallBack;
import com.ep.joy.net.subscribers.ProgressSubscriber;

import java.util.List;

public class FragmentOne extends Fragment {

    private TextView mView;
    private ImageView mImageView;
    private static final String baseUrl = "http://tnfs.tngou.net/image";
    private static final String ARGS_INSTANCE = FragmentOne.class.getSimpleName();
    int mInt;
    private Button btn;
    private int i = 0;
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
        mView = (TextView) view.findViewById(R.id.tv);
        mImageView = (ImageView) view.findViewById(R.id.img);
        btn = (Button) view.findViewById(R.id.next);
        test();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2();
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
