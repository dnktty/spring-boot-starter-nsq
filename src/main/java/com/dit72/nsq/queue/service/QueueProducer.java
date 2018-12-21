package com.dit72.nsq.queue.service;

import com.dit72.nsq.queue.entity.QMessage;
import com.dit72.nsq.queue.util.JsonUtil;
import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.NSQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-11-29 17:23
 **/

public class QueueProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String host;
    private int port;
    //消息生产者
    NSQProducer producer;
    public void send(String topic, Object msg){
        try {
            if(null==producer){
                producer = new NSQProducer().addAddress(this.getHost(), this.getPort()).start();
            }
            producer.produce(topic, JsonUtil.toJsonString(new QMessage(msg)).getBytes());
        } catch (Exception e) {
            logger.error("队列消息发送失败：", e);
        }
    }

    public NSQProducer getProducer() {
        return producer;
    }

    public void setProducer(NSQProducer producer) {
        this.producer = producer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
