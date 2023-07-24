package com.train.schoolplanhive.user.model;

import lombok.Data;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description: 用户实体类
 */


@Data
public class User {
    private Integer id; //用户id
    private String username;
    private String realName; //姓名
    private String email;
    private String mobile; //手机号
    private String gender; //性别
    private String pwd;
    private String portrait; //头像在服务器上的路径
    private int status; //状态 0正常，1 注销
    private int role; //角色 0：普通用户，1：管理员
}
