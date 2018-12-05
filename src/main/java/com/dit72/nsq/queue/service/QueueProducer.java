package com.dit72.nsq.queue.service;

import com.github.brainlag.nsq.NSQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-11-29 17:23
 **/

public class QueueProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String host;
    private int port;
    public void send(String topic, String msg){
        NSQProducer producer = new NSQProducer().addAddress(host, port).start();
        try {
            producer.produce(topic, msg.getBytes());
        } catch (Exception e) {
            logger.error("队列消息发送错误：", e);
        }
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
