package com.wissensalt.rnd.sts.api.service.impl;

import com.wissensalt.rnd.sts.api.dao.UserDAO;
import com.wissensalt.rnd.sts.api.service.IAuthenticationService;
import com.wissensalt.rnd.sts.shared.data.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created on 5/17/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Override
    public User login(String p_UserName) {
        User user = null;
        user = userDAO.findByCode(p_UserName);
        if(user != null) {
            LOGGER.debug("User Found : {}", user.getCode());
            return user;
        }else {
            LOGGER.warn("User Not Found");
            return null;
        }
    }

    @Override
    public void updateStatusLoggedIn(User p_User) {
        p_User.setLastLogin(new Date());
        p_User.setLoginStatus(Boolean.TRUE);
        userDAO.save(p_User);
    }

    @Override
    public void updateStatusLoggedOut(String p_UserName){
        User p_User;
        p_User = userDAO.findByCode(p_UserName);
        if (p_User != null) {
            p_User.setLoginStatus(Boolean.FALSE);
            userDAO.save(p_User);
        }else {
            LOGGER.error("Error Updating Status Logout");
        }
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
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
        } else {
            LOGGER.info("Cookie is Empty, Cancel Process Clearing Cookie");
        }
    }
}
