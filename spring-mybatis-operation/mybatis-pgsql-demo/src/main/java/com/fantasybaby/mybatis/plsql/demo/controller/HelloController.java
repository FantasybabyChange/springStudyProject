package com.fantasybaby.mybatis.plsql.demo.controller;

import com.fantasybaby.mybatis.plsql.demo.entity.TestDO;
import com.fantasybaby.mybatis.plsql.demo.service.ITestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hello/")
public class HelloController {
    @Resource
    private ITestService testService;
    @GetMapping("hello")
    public List<TestDO> testHello(){
        List<TestDO> list = testService.list();
        return list;
    }
}
