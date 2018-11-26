package com.ele.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yc on 2018/7/20.
 */
@RestController
public class HiController {

    @Autowired
    SchedualServiceHi schedualServiceHi;


    @GetMapping(value = "/hi")
    public  String sayHi(@RequestParam String name){
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
