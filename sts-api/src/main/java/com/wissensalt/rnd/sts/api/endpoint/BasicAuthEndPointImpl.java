package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.service.IAuthenticationService;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestFindByNameDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseUserDTO;
import com.wissensalt.rnd.sts.shared.data.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 6/3/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class BasicAuthEndPointImpl implements IAuthEndPoint<RequestLoginDTO, ResponseDataDTO> {
    /**
     *
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthEndPointImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    public ResponseDataDTO logout(HttpServletRequest p_Request, HttpServletResponse p_Response) {
        ResponseDataDTO result = new ResponseDataDTO("201", "Logout Failed");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            authenticationService.logout(p_Request, p_Response, auth);
            LOGGER.info("Logout Successful");
            authenticationService.updateStatusLoggedOut(auth.getName());
            result = new ResponseDataDTO("200", "Logout Success");
        }else {
            LOGGER.error("Logout Failed");
        }
        return result;
    }

    @Override
    public ResponseDataDTO login(@RequestBody RequestLoginDTO p_RequestLoginDTO, HttpServletResponse p_HttpServletResponse, HttpServletRequest p_Request) {
        ResponseDataDTO result = new ResponseDataDTO("201", "Logout Failed");
        if (p_RequestLoginDTO.getPassword() != null && p_RequestLoginDTO.getUserName() != null) {
            UserDetails userDetails;
            userDetails = authenticationService.login(p_RequestLoginDTO.getUserName());
            if (userDetails != null) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(p_RequestLoginDTO.getUserName(), p_RequestLoginDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                result = new ResponseDataDTO("200", "Login Success");
            }else {
                LOGGER.info("User Not Found");
                result = new ResponseDataDTO("404", "User Not Found");
            }
        }else {
            LOGGER.error("Missing Login Body Request");
        }
        LOGGER.info("RESULT "+result.toString());
        return result;
    }

    @PostMapping("/principal")
    public Object loginAndGetPrincipal(@RequestBody RequestLoginDTO p_RequestLoginDTO) {
        if (p_RequestLoginDTO.getPassword() != null && p_RequestLoginDTO.getUserName() != null) {
            UserDetails userDetails;
            userDetails = authenticationService.login(p_RequestLoginDTO.getUserName());
            if (userDetails != null) {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(p_RequestLoginDTO.getUserName(), p_RequestLoginDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return authentication.getPrincipal();
            }else {
                LOGGER.info("User Not Found");
                return null;
            }
        }else {
            LOGGER.error("Missing Login Body Request");
            return null;
        }
    }

    @PostMapping("/findUser")
    public ResponseUserDTO findUser(@RequestBody RequestFindByNameDTO requestFindByNameDTO) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        User user = authenticationService.login(requestFindByNameDTO.getName());
        if (user != null) {
            responseUserDTO.setUserName(user.getUsername());
            responseUserDTO.setAuthorities(user.getAuthorityDTOs());
            return responseUserDTO;
        }else {
            LOGGER.info("User Not Found");
            return null;
        }
    }
}
