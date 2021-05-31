package com.dikar.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dikar.constants.RabbitConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
//@RabbitListener(queues = {RabbitConstants.DEAD_QUEUE, RabbitConstants.DELAY_IMMEDIATE_QUEUE})
public class DelayMqHandler {


    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.DELAY_IMMEDIATE_QUEUE)
    public void receiveMsg(Map<String, Object> msg) {
        JSONObject jsonObject = JSONUtil.parseObj(msg);
        log.info("消费时间为: {}", DateUtil.format(LocalDateTime.now(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        log.info(jsonObject.toString());
    }
}
