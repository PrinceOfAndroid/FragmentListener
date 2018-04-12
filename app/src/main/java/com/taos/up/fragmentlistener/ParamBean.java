package com.taos.up.fragmentlistener;

/**
 * Created by PrinceOfAndroid on 2018/4/12 0012.
 */

public class ParamBean {
    private String msg;
    private int id;

    public ParamBean(String msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
