package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IDepartmentClient {

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/department/findAll")
    List<ResponseDepartmentDTO> findAll(@Param("auth") String p_BasicToken);
}
