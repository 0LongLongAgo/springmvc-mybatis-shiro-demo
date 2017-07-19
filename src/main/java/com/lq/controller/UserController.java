package com.lq.controller;

import com.lq.model.User;
import com.lq.service.UserService;
import com.lq.utils.LogUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zn on 2016/4/11.
 */
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.lq.model.User;
import com.lq.serviceimpl.UserServiceImpl;
//import org.bsframe.web.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
    public @ResponseBody
    User findByUsername(@RequestParam("name") String name) {

        return this.userServiceImpl.findByUsername(name);
    }

    @RequestMapping(value = "/before_login", method = RequestMethod.GET)
    public String beforeLogin(Model model) {
        model.addAttribute("user11", new User());
//        redirectAttributes.addFlashAttribute("hello","注册成功，大家好");

        LogUtils.log("beforeLogin111111111");
        return "login";
    }

    @RequestMapping(value = "/to_register", method = RequestMethod.POST)
    public String toRegister(Model model) {
        model.addAttribute("user11", new User());
//        redirectAttributes.addFlashAttribute("hello","注册成功，大家好");
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String isLogin(@ModelAttribute("user11") User user1, Model model) {
//        model.addAttribute("user11", user1);
//        String loginName = request.getParameter("username");
//        String loginPassword = request.getParameter("password");
        String loginName = user1.getUsername();
        String loginPassword = user1.getPassword();
        LogUtils.log("loginName:" + loginName + "\n" + "loginPassword:" + loginPassword);
//        HttpSession session = request.getSession(true);
        String errorMessage = "";

        Subject user = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
        token.setRememberMe(true);

        try {
            user.login(token);
            String userID = (String) user.getPrincipal();
//            session.setAttribute("USERNAME", userID);
            return "login_success";
        } catch (ExcessiveAttemptsException e) {
            errorMessage = "用户认证失败：" + "密码尝试次数超过五次";
        } catch (UnknownAccountException uae) {
            errorMessage = "用户认证失败：" + "username wasn't in the system.";
        } catch (IncorrectCredentialsException ice) {
            errorMessage = "用户认证失败：" + "password didn't match.";
        } catch (LockedAccountException lae) {
            errorMessage = "用户认证失败：" + "account for that username is locked - can't login.";
        } catch (AuthenticationException e) {
            errorMessage = "登录失败错误信息：" + e;
            e.printStackTrace();
            token.clear();
        }
//        session.setAttribute("ErrorMessage", errorMessage);

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user11") User user1, Model model) {
        String loginName = user1.getUsername();
        String loginPassword = user1.getPassword();
        LogUtils.log("registerName:" + loginName + "\n" + "registerPassword:" + loginPassword);
//        HttpSession session = request.getSession(true);
        String errorMessage = "";

        Subject user = SecurityUtils.getSubject();


        User userInDb = userServiceImpl.findByUsername(loginName);
        if (userInDb != null) {
            errorMessage = "用户名已存在！";
            return "error";
        } else {
            long insertIntoDb = userServiceImpl.createUser(user1);
            if (insertIntoDb > 0) {

                return "login";
            }else{
                errorMessage = "向数据库添加失败！";

            }
        }
//
//        session.setAttribute("ErrorMessage", errorMessage);

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    /**
     * 获取当前用户的用户名
     *
     * @return 跳转到展示当前用户姓名的页面
     */
    @RequestMapping(value = "/getCurrent", method = RequestMethod.GET)
    public ModelAndView getCurrent(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        try {
//            request.getSession().getAttribute("username");
            //获取当前用户的姓名
//            String userName = getLoginUserName();
            String userName = (String) request.getSession().getAttribute("USERNAME");
            //放入到request中
            mv.addObject("userName", userName);
            //设置跳转页面
            mv.setViewName("/top");
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("message", "获取用户出错!");
            mv.setViewName("/exception");
        }
        return mv;
    }


//    @RequestMapping(value="getMenu",method=RequestMethod.GET)
//    public ModelAndView getMenu(HttpServletRequest request,HttpServletResponse response){
//        ModelAndView mav =new ModelAndView();
//        try{
//            List<Sysres> slist= new ArrayList<Sysres>();
//            Sysres s = new Sysres();
//            s.setId(1l);
//            s.setName("通用类别管理");
//            s.setUrl("/category/toCategory");
//            s.setResdesc("toCategory");
//            s.setParentid(2l);
//            slist.add(s);
//
//            mav.setViewName("/left");
//            mav.addObject("menuList", slist);
//            return mav;
//        }catch(Exception e){
//            e.printStackTrace();
//            mav.addObject("message", "获取菜单出错!");
//            mav.setViewName("/exception");
//            return mav;
//        }
//    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        String logout = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login.jsp";
        return logout;
    }


//    /**
//     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
//     */
//    @RequestMapping("/getVerifyCodeImage")
//    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        //设置页面不缓存
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
//        //将验证码放到HttpSession里面
//        request.getSession().setAttribute("verifyCode", verifyCode);
//        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
//        //设置输出的内容的类型为JPEG图像
//        response.setContentType("image/jpeg");
//        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
//        //写给浏览器
//        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
//    }
//
}
//@Controller
//public class UserController {
//    @Resource
//    private UserService userService;
//
//    @RequestMapping("/showUser")
//    public String showUser(HttpServletRequest request, Model model){
//
//        List<User> userList = userService.selectAll();
//        System.out.println("userList:"+userList.toString());
//        model.addAttribute("userList",userList);
//        return "showUser";
//    }
//}