package com.dikar.handler;

import com.dikar.constants.RabbitConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FanoutMqHandler {

//    @RabbitHandler
//    @RabbitListener(queues = RabbitConstants.FANOUT_QUEUE_A)
//    public void handlerAQueue(String message) {
//        log.info("A队列的消息: {}", message);
//    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.FANOUT_QUEUE_B)
    public void handlerBQueue(String message) {
        log.info("B队列的消息: {}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.FANOUT_QUEUE_C)
    public void handlerCQueue(String message) {
        log.info("C队列的消息: {}", message);
    }
}
