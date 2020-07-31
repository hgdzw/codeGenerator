package com.java.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是controller
 *
 * @author dongzhiwei
 * @date 2020/7/30 17:51
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/123")
    public String get() {
        System.out.println("123");
        return "eqweqwe";
    }

}
