package com.dit72.nsq.queue.util;

import com.alibaba.fastjson.TypeReference;
import com.dit72.nsq.queue.entity.QMessage;
import com.github.brainlag.nsq.NSQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: Aaron Chen
 * @date 2018-12-21 9:08
 **/
public class QMessageUtil {

    static private Logger logger = LoggerFactory.getLogger(QMessageUtil.class);

    public static <T> QMessage<T> parse(NSQMessage messsage, Class<T> t){
        return JsonUtil.json2Object(new String(messsage.getMessage()), new TypeReference<QMessage<T>>(t){});
    }

}
