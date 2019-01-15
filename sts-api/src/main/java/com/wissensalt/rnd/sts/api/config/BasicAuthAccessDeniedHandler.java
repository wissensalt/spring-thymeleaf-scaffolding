package com.wissensalt.rnd.sts.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 6/4/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class BasicAuthAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticationFilter.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("WWW-Authenticate", "Basic realm=Authorization/client");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication  != null) {
            LOGGER.warn("User ".concat(authentication.getName()).concat(" attempted to access the protected Path : ".concat(httpServletRequest.getRequestURI())));
        }else {
            LOGGER.warn("Anonymous user attempted to access the protected path : ".concat(httpServletRequest.getRequestURI()));
        }
        ResponseDataDTO responseData = new ResponseDataDTO("404", "Un Authorized");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().println(objectMapper.writeValueAsString(responseData));
    }
}
