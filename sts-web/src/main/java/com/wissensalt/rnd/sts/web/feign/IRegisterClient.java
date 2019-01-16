package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestRegisterDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import feign.Headers;
import feign.RequestLine;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IRegisterClient {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @RequestLine("POST /registration/register")
    ResponseDataDTO register(RequestRegisterDTO p_RequestRegisterDTO);
}
