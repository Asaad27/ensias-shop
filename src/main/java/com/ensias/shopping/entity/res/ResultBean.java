package com.ensias.shopping.entity.res;

import java.io.Serializable;


public class ResultBean<T> implements Serializable {

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    public static final String SUCC_MSG = "succes";

    private String message = SUCC_MSG;
    private int state = SUCCESS;

    //donnes recues
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.message = e.getMessage();
        this.state = FAIL;
    }

    public ResultBean(String message) {
        super();
        this.message = message;
        this.state = FAIL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "message='" + message + '\'' +
                ", state=" + state +
                ", data=" + data +
                '}';
    }
}
