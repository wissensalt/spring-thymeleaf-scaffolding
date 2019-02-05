package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
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

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/department/findPagination")
    ResponsePaginationDTO<ResponseDepartmentDTO> findPagination(@Param("auth") String p_BasicAuth, RequestPaginationDTO p_RequestPaginationDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/department/delete?id={id}")
    ResponseDataDTO delete(@Param("auth") String p_BasicAuth, @Param("id") Long p_Id);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/department/insert")
    ResponseDataDTO insert(@Param("auth") String p_BasicAuth, RequestInsertDepartmentDTO p_RequestInsertDepartmentDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/department/view?id={id}")
    ResponseDepartmentDTO view(@Param("auth") String p_BasicAuth, @Param("id") Long p_Id);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/department/update")
    ResponseDataDTO update(@Param("auth") String p_BasicAuth, ResponseDepartmentDTO p_ResponseDepartmentDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/department/selectLOV")
    List<ResponseLOVDTO> selectLOV(@Param("auth") String p_BasicAuth);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/department/count")
    ResponseDataDTO count(@Param("auth") String p_BasicAuth);
}
