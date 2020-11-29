package com.dikar.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dikar.config.RabbitConfig;
import com.dikar.constants.RabbitConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping(value = "/sendMsg")
    public String sendMsg(
            @RequestParam(value = "msg") String msg,
            @RequestParam(value = "beImmediate") Integer beImmediate
    ) {
        Map<String, Object> messageBody = new HashMap<>();
        messageBody.put("name", "张三");
        messageBody.put("msg", StrUtil.format("这条消息延时{}秒", msg));
        log.info("发送的消息是: {}, 发送时间为: {}", JSONUtil.parseObj(messageBody).toString(),
                DateUtil.format(LocalDateTime.now(), DatePattern.CHINESE_DATE_TIME_PATTERN));
        if (beImmediate == 0) {
            rabbitTemplate.convertAndSend(RabbitConstants.DELAY_QUEUE, messageBody, message -> {
                // 设置10s过期
                message.getMessageProperties().setExpiration(String.valueOf(Integer.parseInt(msg) * 1000));
                return message;
            });
        } else {
            // 使用延时插件, 此处设置延时是setDelay,区别于死信队列的设置是setExpiration
            rabbitTemplate.convertAndSend(RabbitConstants.DELAY_IMMEDIATE_EXCHANGE, RabbitConstants.DELAY_IMMEDIATE_ROUTING_KEY,
                    messageBody, message -> {
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        message.getMessageProperties().setDelay(Integer.parseInt(msg) * 1000);
                        return message;
                    });
        }

        return Boolean.TRUE.toString();
    }

}
