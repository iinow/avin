package com.segeg.avin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/av")
public class AvController {
    
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
