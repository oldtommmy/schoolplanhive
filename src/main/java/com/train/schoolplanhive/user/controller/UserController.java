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
 * @Description: User
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @RequestMapping("/update")
    public void update(String username, String realname, String mobile,
                        String email, String gender) {
        User user = new User();
        user.setUsername(username);
        user.setRealName(realname);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setGender(gender);
        userService.update(user);
    }

    @RequestMapping("/profilemodify.html")
    public String toModify() {
        return "profilemodify.html";
    }

    @RequestMapping("/toProfile")
    public String toEditProfile() {
        return "myprofile.html";
    }

    @RequestMapping("/recover-password.html")
    public String toRecover() {
        return "/recover-password.html";
    }

    @RequestMapping("/doRecover")
    public String doRecover(String emailOrUsername) {
        String result = userService.recoverPwd(emailOrUsername);
        return "/recover-password.html";
    }

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
