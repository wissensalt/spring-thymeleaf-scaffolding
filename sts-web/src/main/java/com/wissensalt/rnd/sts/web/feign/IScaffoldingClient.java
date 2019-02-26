package com.wissensalt.rnd.sts.web.feign;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationCustom;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import feign.Param;

import java.util.List;

/**
 * Created on 1/25/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <REQUEST>
 * @param <RESPONSE>
 */
public interface IScaffoldingClient<REQUEST, RESPONSE> {

    List<RESPONSE> conductFindAll(String p_BasicAuth);

    ResponsePaginationDTO<RESPONSE> conductFindPagination(String p_BasicAuth, RequestPaginationDTO p_RequestPaginationDTO);

    ResponseDataDTO conductDelete(String p_BasicAuth, Long p_Id);

    ResponseDataDTO insert(String p_BasicAuth, REQUEST p_Request);

    RESPONSE view(String p_BasicAuth, Long p_Id);

    ResponseDataDTO update(String p_BasicAuth, RESPONSE p_UpdateDTO);

    List<ResponseLOVDTO> selectLOV(String p_BasicAuth);

    ResponseDataDTO count(String p_BasicAuth);

    ResponsePaginationDTO<RESPONSE> findPaginationCustom(@Param("auth") String p_BasicAuth, RequestPaginationCustom p_RequestPaginationCustom);
}
