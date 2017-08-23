package com.getjavajob.bezfamilnyydg.servlets.controllers;

import com.getjavajob.bezfamilnyydg.models.Account;
import com.getjavajob.bezfamilnyydg.service.interfaces.AccountService;
import com.getjavajob.bezfamilnyydg.servlets.common.util.SessionHelper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.getjavajob.bezfamilnyydg.servlets.common.Constants.*;

@Controller
public class LogIn {
    @Autowired
    private AccountService accountService;
    private final String isRememberMe = IS_REMEMBER_ME;

    @RequestMapping("/LogIn")
    public String toLogInPage() {
        return LOG_IN_PAGE;
    }

    @RequestMapping("/LogInLogic")
    public String logInLogic(@RequestParam(EMAIL) String email, @RequestParam(PASSWORD) String password,
                             @RequestParam(value = isRememberMe, required = false) String isRemember, HttpServletResponse response,
                             HttpServletRequest request, HttpSession session) {

        // get account from DB
        Account account = accountService.getByEmail(email);
        // check for correct data from request
        if (account != null) {

            // check password
            if (checkPassword(account.getPassword(), password)) {

                // is rememberMe
                if (isRemember != null) {
                    addUserIdCookie(response, account);
                }

                // user logging
                SessionHelper.addInfoForLoggedUserInSession(request, account);
                return "redirect:/PersonalPage";

            }
        }

        // if logged failed
        SessionHelper.setIsLoggedFailed(request);
        return "redirect:/LogIn";

    }

    private boolean checkPassword(String passwordFromDB, String passwordFromForm) {
        return BCrypt.checkpw(passwordFromForm, passwordFromDB);
    }

    private void addUserIdCookie(HttpServletResponse response, Account account) {
        Cookie cookie = new Cookie(COOKIE_USER_ID, String.valueOf(account.getId()));
        response.addCookie(cookie);
    }

}
