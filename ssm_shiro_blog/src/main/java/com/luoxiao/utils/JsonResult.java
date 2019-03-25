package com.luoxiao.utils;

import java.io.Serializable;

public class JsonResult implements Serializable {

    private static final long serialVersionUID = 3194407306649544324L;
    private int state=1;
    //正确数据
    private String message="ok";
    private Object data;

    public JsonResult(String message){
        this.message=message;
    }
    public JsonResult(Object data){
        this.data=data;
    }

    public JsonResult(Throwable e){
        this.message= e.getMessage();
        state=0;
    }

    public JsonResult() {

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
