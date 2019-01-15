package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 1/3/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/auth")
public interface IAuthEndPoint<LOGIN_DATA, RESPONSE_DTO> {

    @PostMapping("/logout")
    ResponseDataDTO logout(HttpServletRequest p_Request, HttpServletResponse p_Response);

    @ResponseBody
    @PostMapping("/login")
    RESPONSE_DTO login(@RequestBody LOGIN_DATA login_data, HttpServletResponse p_Response, HttpServletRequest p_Request);
}
