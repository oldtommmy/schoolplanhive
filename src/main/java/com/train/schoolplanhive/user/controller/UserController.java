package com.train.schoolplanhive.user.controller;

import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description:
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    @RequestMapping("tologin")
    public String toLogin(){
        LOGGER.debug("begin to login>>>>>>>>>");
        return "login";
    }
    @RequestMapping("dologin")
    public String doLogin(String username,
                          String pwd,
                          HttpSession session,
                          HttpServletRequest request){
        LOGGER.info(username +" begin login>>>>>");
        User loginUser = userService.login(username);
        if(loginUser == null){
            LOGGER.error("用户名或者密码错误！");
            return "login";
        }else{
            if(loginUser.getPwd().equals(pwd)){
                return "index";
            }else{
                LOGGER.error("用户名或者密码错误！");
                return "login";
            }
        }
    }
}
