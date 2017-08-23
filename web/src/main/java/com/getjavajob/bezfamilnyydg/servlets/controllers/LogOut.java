package com.getjavajob.bezfamilnyydg.servlets.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.bezfamilnyydg.servlets.common.util.SessionHelper.logOut;

@Controller
public class LogOut {

    @RequestMapping("/LogOutLogic")
    public String logOutLogic(HttpSession session, HttpServletResponse response) {
        logOut(session, response);
        return "redirect:/LogIn";
    }

}
