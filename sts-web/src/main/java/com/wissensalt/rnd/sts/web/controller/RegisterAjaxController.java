package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestRegisterDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.web.feign.RegisterClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
@RequestMapping("/register")
public class RegisterAjaxController {

    @Autowired
    private RegisterClientImpl registerClient;

    @PostMapping("/perform")
    public ResponseDataDTO performRegister(@RequestBody RequestRegisterDTO requestRegisterDTO) {
        return registerClient.conductPost(requestRegisterDTO);
    }

}
