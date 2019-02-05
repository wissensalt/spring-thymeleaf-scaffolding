package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
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
public interface IEmployeeClient {

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/employee/findAll")
    List<ResponseEmployeeDTO> findAll(@Param("auth") String p_BasicToken);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/employee/findPagination")
    ResponsePaginationDTO<ResponseEmployeeDTO> findPagination(@Param("auth") String p_BasicAuth, RequestPaginationDTO p_RequestPaginationDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/employee/delete?id={id}")
    ResponseDataDTO delete(@Param("auth") String p_BasicAuth, @Param("id") Long p_Id);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/employee/insert")
    ResponseDataDTO insert(@Param("auth") String p_BasicAuth, RequestInsertEmployeeDTO p_RequestInsertEmployeeDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/employee/view?id={id}")
    ResponseEmployeeDTO view(@Param("auth") String p_BasicAuth, @Param("id") Long p_Id);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("POST /api/employee/update")
    ResponseDataDTO update(@Param("auth") String p_BasicAuth, ResponseEmployeeDTO p_ResponseEmployeeDTO);

    @Headers({"Accept: application/json", "Content-Type: application/json", "Authorization: {auth}"})
    @RequestLine("GET /api/employee/count")
    ResponseDataDTO count(@Param("auth") String p_BasicAuth);
}
