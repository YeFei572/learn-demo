package com.dikar.upaidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UpaiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpaiDemoApplication.class, args);
    }


    @RequestMapping(value = "/test")
    public String test() {
        System.out.println("test");
        return "test";
    }
}
