package com.dikar.config;

import com.dikar.constants.RabbitConstants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    /**
     * 延时队列, 设置死信交换机和死信路由绑定
     *
     * @return
     */
    @Bean("delayQueue")
    public Queue delayQueue() {
        return QueueBuilder.durable(RabbitConstants.DELAY_QUEUE)
                // 如果消息过时, 则会被投递到当前对应的死信交换机上
                .withArgument("x-dead-letter-exchange", RabbitConstants.DEAD_EXCHANGE)
                // 如果消息过时, 死信交换机会根据延时队列路由投递到对应的队列
                .withArgument("x-dead-letter-routing-key", RabbitConstants.DELAY_ROUTING_KEY)
                .build();
    }

    /**
     * 定义死信队列
     *
     * @return
     */
    @Bean("deadQueue")
    public Queue deadQueue() {
        // 设置死信交换机及路由key
        return QueueBuilder.durable(RabbitConstants.DEAD_QUEUE).build();
    }

    /**
     * 定义死信交换机
     *
     * @return
     */
    @Bean("deadExchange")
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange(RabbitConstants.DEAD_EXCHANGE).build();
    }

    @Bean("deadBinding")
    public Binding deadBinding(@Qualifier("deadExchange") Exchange deadExchange, @Qualifier("deadQueue") Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(RabbitConstants.DELAY_ROUTING_KEY).noargs();
    }


    @Bean("immediateQueue")
    public Queue immediateQueue() {
        return QueueBuilder.durable(RabbitConstants.DELAY_IMMEDIATE_QUEUE).build();
    }

    @Bean("immediateExchange")
    public Exchange immediateExchange() {
        DirectExchange directExchange = new DirectExchange(RabbitConstants.DELAY_IMMEDIATE_EXCHANGE, true, false);
        directExchange.setDelayed(Boolean.TRUE);
        return directExchange;
    }

    @Bean
    public Binding bindingImmediateDelay(
            @Qualifier("immediateExchange") Exchange immediateExchange,
            @Qualifier("immediateQueue") Queue immediateQueue) {
        return BindingBuilder.bind(immediateQueue).to(immediateExchange)
                .with(RabbitConstants.DELAY_IMMEDIATE_ROUTING_KEY).noargs();
    }

}

