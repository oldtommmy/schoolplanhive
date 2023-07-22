package com.train.schoolplanhive.user.controller;

import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @ApiOperation("退出登录")
    @RequestMapping ("login.html")
    public String toLogout() {
        LOGGER.info("Logout");
        return "login";
    }

    @ApiOperation("前往登录页面")
    @RequestMapping ("toLogin") //http://localhost:8080/user/toLogin
    public String toLogin(){
        LOGGER.debug("begin to login>>>>>>>>>");
        return "login";
    }

    @ApiOperation("登录接口")
    @RequestMapping ("doLogin") //http://localhost:8080/user/toLogin
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
                session.setAttribute("status", "Error");
                LOGGER.error("用户名或者密码错误！");
                return "login";
            }
        }
    }
}
