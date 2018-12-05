package com.dit72.nsq.queue.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-11-30 16:21
 **/
@ConfigurationProperties(prefix = "mq.consumer")
public class QueueConsumerProperties {
    String host;
    int port;

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
