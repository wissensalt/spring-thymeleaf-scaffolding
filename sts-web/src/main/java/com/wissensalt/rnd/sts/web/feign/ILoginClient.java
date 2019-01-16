package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.response.RequestFindByNameDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.RequestLoginDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePrincipalDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseUserDTO;
import feign.Headers;
import feign.RequestLine;

/**
 * Created on 1/4/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface ILoginClient {

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @RequestLine("POST /auth/principal")
    ResponsePrincipalDTO login(RequestLoginDTO p_RequestLoginDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @RequestLine("POST /auth/findUser")
    ResponseUserDTO findUser(RequestFindByNameDTO p_RequestFindByNameDTO);
}
