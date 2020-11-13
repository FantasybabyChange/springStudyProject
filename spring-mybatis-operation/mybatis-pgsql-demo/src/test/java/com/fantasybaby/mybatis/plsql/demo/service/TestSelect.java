package com.fantasybaby.mybatis.plsql.demo.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fantasybaby.mybatis.plsql.MybatisPgsqlDemoApplicationTests;
import com.fantasybaby.mybatis.plsql.demo.entity.TestDO;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class TestSelect extends MybatisPgsqlDemoApplicationTests {
    @Resource
    private ITestService testService;

    @Test
    public void testQuery() {
        List<TestDO> list = testService.list();
        System.out.println(list);
    }

    @Test
    public void testInsert() {
        TestDO test = new TestDO();
        test.setAge(13);
        test.setName("tiancai");
        boolean save = testService.save(test);
        System.out.println(save);
    }

    @Test
    public void testUpdate() {
        UpdateWrapper update = new UpdateWrapper();
        update.eq("name", "tiancai");
        update.set("paasword", "321_");
        boolean save = testService.update(update);
        System.out.println(save);
    }
}
