package com.dit72.nsq.queue.config;

import com.dit72.nsq.queue.entity.QueueConsumerProperties;
import com.dit72.nsq.queue.service.QueueConsumer;
import com.github.brainlag.nsq.lookup.DefaultNSQLookup;
import com.github.brainlag.nsq.lookup.NSQLookup;
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
@EnableConfigurationProperties(value = QueueConsumerProperties.class)
@ConditionalOnClass(QueueConsumer.class)
@ConditionalOnProperty(prefix = "mq.consumer", name="host",  matchIfMissing = false)
public class QueueConsumerAutoConfiguration {
    @Autowired
    private QueueConsumerProperties queueConsumerProperties;

    @Bean
    @ConditionalOnMissingBean(QueueConsumer.class)
    public QueueConsumer queueConsumer() {
        QueueConsumer queueConsumer = new QueueConsumer();
        queueConsumer.setHost(queueConsumerProperties.getHost());
        queueConsumer.setPort(queueConsumerProperties.getPort());
        return queueConsumer;
    }
}
