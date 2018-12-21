package com.dit72.nsq.queue.enums;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-12-20 19:22
 **/
public enum QMessageStateEnum {
    BROADCASTED,//已发出
    RECEIVED,//已接收
    FINISHED,//已处理
    DEFAULT;
}
