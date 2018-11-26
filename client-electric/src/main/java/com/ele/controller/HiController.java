package com.ele.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yc on 2018/11/26.
 */
@RestController
public class HiController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name",defaultValue = "yc") String name){
        return " hi "+name+" , i am from port:"+port+" 我不管我最帅";
    }


    public String hiError(String name){
        return " hi "+name+", sorry error";
    }
}
