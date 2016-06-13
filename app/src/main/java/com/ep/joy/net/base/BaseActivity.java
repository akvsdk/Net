package com.ep.joy.net.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * author   Joy
 * Date:  2016/6/13.
 * version:  V1.0
 * Description:
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseAppManager.getInstance().addActivity(this);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
            getsavedInstanceState(savedInstanceState);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        initViewsAndEvents();
    }


    /**
     * show toast
     *
     * @param msg
     */
    protected void showSnackbar(View v, String msg) {
        //防止遮盖虚拟按键
        if (null != msg && !TextUtils.isEmpty(msg)) {
            Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }


    /**
     * startActivity
     *
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }


    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }


    protected void getBundleExtras(Bundle extras) {
    }

    protected void getsavedInstanceState(Bundle savedInstanceState) {
    }

    protected abstract int getContentViewLayoutID();

    protected abstract void initViewsAndEvents();
}
