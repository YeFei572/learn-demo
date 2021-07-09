package com.dikar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Slf4j
@RestController
//@RequestMapping(value = "/test")
@RequiredArgsConstructor
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/sendMsg")
//    @Scheduled(cron = "0/4 * * * * ? ")
    public Boolean sendMsg(String msg) {
        log.info("----------------------{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        simpMessagingTemplate.convertAndSendToUser("1", "/remind", new Random().nextInt(30) + msg);
        return Boolean.TRUE;
    }

    @GetMapping(value = "/test")
    public void test(@RequestParam String msg) {
        simpMessagingTemplate.convertAndSendToUser("1", "/remind", new Random().nextInt(100) + msg);
    }

    @GetMapping(value = "/push")
    public void pushMsg(@RequestParam String msg) {
        simpMessagingTemplate.convertAndSend("/notice/*/remind", "推送内容");
    }
}
