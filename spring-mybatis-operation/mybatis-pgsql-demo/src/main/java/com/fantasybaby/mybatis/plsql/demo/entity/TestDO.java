package com.fantasybaby.mybatis.plsql.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author fantasybaby
 * @since 2020-11-13
 */
@Data
@TableName("Test")
@EqualsAndHashCode(callSuper = false)
public class TestDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private String paasword;


}
