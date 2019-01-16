package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.web.CookieUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping("/perform")
    public String performLogout(HttpServletRequest p_Request, HttpServletResponse p_Response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            CookieUtil.clearBasicAuth(p_Request, p_Response, auth);
            new SecurityContextLogoutHandler().logout(p_Request, p_Response, auth);
            auth.setAuthenticated(false);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        return "admin";
    }
}
