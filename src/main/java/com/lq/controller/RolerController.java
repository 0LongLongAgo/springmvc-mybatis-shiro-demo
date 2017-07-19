package com.lq.controller;

import com.lq.model.User;
import com.lq.serviceimpl.UserServiceImpl;
import com.lq.utils.LogUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zn on 2016/4/11.
 */
//import org.bsframe.web.util.VerifyCodeUtil;

@Controller
@RequestMapping("/role")
public class RolerController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
    public @ResponseBody
    User findByUsername(@RequestParam("name") String name) {

        return this.userServiceImpl.findByUsername(name);
    }

    @RequestMapping(value = "/testAuthc", method = RequestMethod.POST)
    public String beforeLogin(Model model) {
        model.addAttribute("user11", new User());

        return "role";
    }

}