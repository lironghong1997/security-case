package com.securitydemo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @version:1.0.0
* @author: lironghong
* @date: 2018/12/31 15:24
* @description: 
*/
@RestController
public class IndexController {
    @RequestMapping("/")
    public String in(){
        return "Hello springboot-security!";
    }
    @RequestMapping("/hello")
    public String Hello(){
        return "经过授权!";
    }
    //表示只有管理员才能访问
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/role")
    public String role(){
        return "you is admin!";
    }
}
