package com.dit72.nsq.queue.config;

import com.dit72.nsq.queue.entity.QueueProducerProperties;
import com.dit72.nsq.queue.service.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-11-30 16:22
 **/
@Configuration
@EnableConfigurationProperties(value = QueueProducerProperties.class)
@ConditionalOnClass(QueueProducer.class)
@ConditionalOnProperty(prefix = "mq.producer", name="host", matchIfMissing = false)
public class QueueProducerAutoConfiguration {
    @Autowired
    private QueueProducerProperties queueProducerProperties;

    @Bean
    @ConditionalOnMissingBean(QueueProducer.class)
    public QueueProducer queueProducer() {
        QueueProducer queueProducer = new QueueProducer();
        queueProducer.setHost(queueProducerProperties.getHost());
        queueProducer.setPort(queueProducerProperties.getPort());
        return queueProducer;
    }
}
