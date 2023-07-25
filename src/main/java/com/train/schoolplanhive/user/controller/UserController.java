package com.train.schoolplanhive.user.controller;

import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping("/toRegister")
    public String register(Model model, HttpSession session,
                           String username, String realName, String email,
                           String pwd, String repwd) {
        if (!pwd.equals(repwd)) {
            model.addAttribute("pwdStatus", "error");
            return "register";
        }
        User serviceUser = userService.getUser(username);
        if (serviceUser != null) {
            model.addAttribute("nameStatus", "error");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setRealName(realName);
        user.setEmail(email);
        user.setPwd(pwd);
        userService.add(user);
        user = userService.getUser(username);
        model.addAttribute("user", user);
        model.addAttribute("nameStatus", "success");
        model.addAttribute("pwdStatus", "success");
        session.removeAttribute("user");
        session.setAttribute("user", user);
        return "index";
    }

    @RequestMapping("/register.html")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/update")
    public String update(String username, String realName, String mobile,
                        String email, String gender, String id, Model model, HttpSession session) {
        User userById = userService.getUserById(Integer.parseInt(id));
        userById.setUsername(username);
        userById.setRealName(realName);
        userById.setMobile(mobile);
        userById.setEmail(email);
        userById.setGender(gender);
        userService.update(userById);
        session.removeAttribute("user");
        session.setAttribute("user", userById);
        model.addAttribute("user", userById);
        return "myprofile";
    }

    @RequestMapping("/profilemodify.html")
    public String toModify(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "profilemodify.html";
    }

    @RequestMapping("/toProfile")
    public String toEditProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "myprofile";
    }

    @RequestMapping("/recover-password.html")
    public String toRecover() {
        return "/recover-password";
    }

    @RequestMapping("/doRecover")
    public String doRecover(String emailOrUsername, Model model) {
        String result = userService.recoverPwd(emailOrUsername);
        return "/recover-password.html";
    }

    @ApiOperation("退出登录")
    @RequestMapping ("login.html")
    public String toLogout(HttpSession session) {
        LOGGER.info("Logout");
        session.removeAttribute("user");
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
                          Model model,
                          HttpServletRequest request) throws InterruptedException {
        LOGGER.info(username +" begin login>>>>>");
        User loginUser = userService.login(username);
        if(loginUser == null){
            LOGGER.error("用户名或者密码错误");
            model.addAttribute("loginStatus", "error");
            return "login";
        }else{
            if(loginUser.getPwd().equals(pwd)){
                session.setAttribute("user", loginUser);
                model.addAttribute("user", loginUser);
                model.addAttribute("loginStatus", "success");
                System.out.println(loginUser);
                return "index";
            }else{
                model.addAttribute("loginStatus", "error");
                LOGGER.error("用户名或者密码错误！");
                return "login";
            }
        }
    }

    @RequestMapping ("changepwd.html")
    public String toChangePwd(Model model, HttpSession session) {
        model.addAttribute(session.getAttribute("user"));
        return "changepwd";
    }

    @RequestMapping("changePwd")
    public String changePwd(Model model, HttpSession session, String oldpwd, String pwd, String repwd) {
        model.addAttribute(session.getAttribute("user"));
        User user = userService.getUserById(((User) session.getAttribute("user")).getId());
        if (user.getPwd().equals(oldpwd)) {
            if (pwd.equals(repwd)) {
                user.setPwd(pwd);
                userService.updatePwd(user);
                model.addAttribute("changeStatus", "success");
                session.removeAttribute("user");
                return "login";
            }
        }
        model.addAttribute("changeStatus", "error");
        return "changepwd";
    }
}
