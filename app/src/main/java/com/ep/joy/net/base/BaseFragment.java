package com.ep.joy.net.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author   Joy
 * Date:  2016/6/13.
 * version:  V1.0
 * Description:
 */

public abstract class BaseFragment extends RxFragment {
    private View mViewContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mViewContent == null) {
            if (getContentViewLayoutID() != 0) {
                mViewContent = inflater.inflate(getContentViewLayoutID(), container, false);
            } else {
                throw new IllegalArgumentException("You must return a right contentView layout resource Id");
            }

        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        return mViewContent;

    }

    protected void showSnackbar(View v, String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !TextUtils.isEmpty(msg)) {
            Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        observable
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    protected abstract int getContentViewLayoutID();

    protected abstract void initView(View view);

    protected abstract void initData();
}
