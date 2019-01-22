package com.wissensalt.rnd.sts.web;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class SessionUtil {

    public static HttpSession createSession(HttpServletRequest p_HttpServletRequest, RequestLoginDTO p_RequestLoginDTO, SecurityContext sc) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
        String basicAuth = p_RequestLoginDTO.getUserName().concat(":").concat(p_RequestLoginDTO.getPassword());
        session.setAttribute("basicAuth", Base64.getEncoder().encodeToString(basicAuth.getBytes()));
        session.setAttribute("userName", p_RequestLoginDTO.getUserName());
        return session;
    }

    public static void destroySession(HttpServletRequest p_HttpServletRequest) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        session.removeAttribute("basicAuth");
        session.removeAttribute("userName");
        session.removeAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    }
}
