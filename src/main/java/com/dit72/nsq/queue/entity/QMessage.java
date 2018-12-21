package com.dit72.nsq.queue.entity;

import com.dit72.nsq.queue.enums.QMessageStateEnum;
import sun.security.provider.MD5;

import java.util.UUID;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-12-20 19:19
 **/
public class QMessage<T> {

    public QMessage(T data){
        this.mid = UUID.randomUUID().toString();
        this.data = data;
        this.state = QMessageStateEnum.BROADCASTED.name();
    }
    //消息id
    String mid;
    //消息状态
    String state;
    //消息数据
    T data;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
