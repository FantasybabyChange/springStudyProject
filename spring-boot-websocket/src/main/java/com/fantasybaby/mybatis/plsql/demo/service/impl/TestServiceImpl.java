package com.fantasybaby.mybatis.plsql.demo.service.impl;

import com.fantasybaby.mybatis.plsql.demo.entity.TestDO;
import com.fantasybaby.mybatis.plsql.demo.mapper.TestMapper;
import com.fantasybaby.mybatis.plsql.demo.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fantasybaby
 * @since 2020-11-13
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestDO> implements ITestService {

}
