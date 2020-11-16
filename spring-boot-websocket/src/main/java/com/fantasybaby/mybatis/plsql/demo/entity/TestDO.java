package com.fantasybaby.mybatis.plsql.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @TableField("password")
    private String password;


}
