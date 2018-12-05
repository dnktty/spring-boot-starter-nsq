package com.dit72.nsq.queue.service;

import com.github.brainlag.nsq.NSQConsumer;
import com.github.brainlag.nsq.callbacks.NSQMessageCallback;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-11-29 17:23
 **/
public class QueueConsumer{

    private String host; //注意要配置host映射
    private int port;


    public void listener(String topic, String channel, NSQMessageCallback callback){
        NSQLookup lookup = new DefaultNSQLookup();
        lookup.addLookupAddress(host, port);
        NSQConsumer consumer = new NSQConsumer(lookup, topic, channel, callback);
        consumer.start();
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
