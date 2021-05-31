package com.dikar.controller;

import com.dikar.components.StarCoinHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final StarCoinHandler starCoinHandler;

    @GetMapping(value = "/test")
    public void test() throws InterruptedException {
        starCoinHandler.handlerStarCoin();
    }

}
