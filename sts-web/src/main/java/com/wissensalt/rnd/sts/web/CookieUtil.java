package com.wissensalt.rnd.sts.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class CookieUtil {

    public static void clearBasicAuth(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        if (httpServletRequest.getCookies() != null) {
            for(Cookie cookie : httpServletRequest.getCookies()) {
                if(cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    cookie.setPath(httpServletRequest.getContextPath());
                    httpServletResponse.addCookie(cookie);
                    //Clear the other cookie
                    Cookie cookieWithSlash = (Cookie) cookie.clone();
                    cookieWithSlash.setPath(httpServletRequest.getContextPath() + "/");
                    httpServletResponse.addCookie(cookieWithSlash);
                }
            }
            new CookieClearingLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
    }

}
