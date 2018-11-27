package com.ele.controller;

import com.ele.entity.EO.User;
import com.ele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yc on 2018/11/26.
 */
@RestController
public class HiController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    //@HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name",defaultValue = "yc") String name){
        return " hi "+name+" , i am from port:"+port+" 我不管我最帅  ";
    }


    public String hiError(String name){
        return " hi "+name+", sorry error";
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        try {
            return userService.getUserList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
