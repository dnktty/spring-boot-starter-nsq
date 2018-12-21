package com.dit72.nsq.queue.service;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
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
public class QueueConsumer{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String host; //注意要配置host映射
    private int port;
    private NSQLookup lookup;

    public void listener(String topic, String channel, NSQMessageCallback callback){
        try {
            if(null==lookup){
                lookup = new DefaultNSQLookup();
                lookup.addLookupAddress(this.getHost(), this.getPort());
            }

            new NSQConsumer(lookup, topic, channel, callback).start();
        } catch (Exception e) {
            logger.error("初始化消息队列消费者失败", e);
        }
    }

    public NSQLookup getLookup() {
        return lookup;
    }

    public void setLookup(NSQLookup lookup) {
        this.lookup = lookup;
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
