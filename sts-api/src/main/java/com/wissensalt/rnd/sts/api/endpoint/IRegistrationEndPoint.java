package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestRegisterDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/registration")
public interface IRegistrationEndPoint {

    @PostMapping("/register")
    ResponseDataDTO register(@RequestBody RequestRegisterDTO requestRegisterDTO);
}
