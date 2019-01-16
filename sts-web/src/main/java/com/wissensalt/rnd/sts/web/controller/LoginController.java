package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 1/4/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/perform")
    public ResponseDataDTO performLogin(HttpServletRequest httpServletRequest, @RequestBody RequestLoginDTO requestLoginDTO) {
        LOGGER.info("Perform Login");
        ResponseDataDTO response = new ResponseDataDTO("201", "Login Failed");
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(requestLoginDTO.getUserName(), requestLoginDTO.getPassword());
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
        if (sc.getAuthentication().isAuthenticated()) {
            response = new ResponseDataDTO("200", "Login Success");
        }
        return response;
    }
}
