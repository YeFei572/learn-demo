package com.dikar.constants;

public interface RabbitConstants {

    /**
     * 死信队列
     */
    String DEAD_QUEUE = "com.dikar.dead.queue";
    /**
     * 死信交换机
     */
    String DEAD_EXCHANGE = "com.dikar.dead.exchange";
    /**
     * 延时队列
     */
    String DELAY_QUEUE = "com.dikar.delay.queue";
    /**
     * 延时路由key
     */
    String DELAY_ROUTING_KEY = "com.dikar.delay.routingKey";
    /**
     * 延时队列(瞬时插件)
     */
    String DELAY_IMMEDIATE_QUEUE = "com.dikar.delay.immediate.queue";
    /**
     * 延时交换机(瞬时插件)
     */
    String DELAY_IMMEDIATE_EXCHANGE = "com.dikar.delay.immediate.exchange";
    /**
     * 延时路由(瞬时插件)
     */
    String DELAY_IMMEDIATE_ROUTING_KEY = "com.dikar.delay.immediate.routingKey";

    /**
     * 广播模式交换
     */
    String FANOUT_EXCHANGE = "com.dikar.fanoutExchange";

    /**
     * 广播模式队列A,B,C
     */
    String FANOUT_QUEUE_A = "com.dikar.fanoutQueue.A";
    String FANOUT_QUEUE_B = "com.dikar.fanoutQueue.B";
    String FANOUT_QUEUE_C = "com.dikar.fanoutQueue.C";
}
