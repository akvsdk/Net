package com.ep.joy.net.bean;

/**
 * author   Joy
 * Date:  2016/5/17.
 * version:  V1.0
 * Description:
 */
public class JsonResult<T> {
    private boolean status;
    private int total;
    private T tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T gettngou() {
        return tngou;
    }

    public void settngou(T tngou) {
        this.tngou = tngou;
    }


}
