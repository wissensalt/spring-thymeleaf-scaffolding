package com.wissensalt.rnd.sts.web.feign.impl;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.feign.FeignBuilderFactory;
import com.wissensalt.rnd.sts.web.feign.IDepartmentClient;
import com.wissensalt.rnd.sts.web.feign.IScaffoldingClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class DepartmentClientImpl implements IScaffoldingClient<RequestInsertDepartmentDTO, ResponseDepartmentDTO> {

    @Value("${api.base-path}")
    private String apiBasePath;

    @Override
    public List<ResponseDepartmentDTO> conductFindAll(String p_BasicAuth) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.findAll("Basic "+p_BasicAuth);
    }

    @Override
    public ResponsePaginationDTO<ResponseDepartmentDTO> conductFindPagination(String p_BasicAuth, RequestPaginationDTO p_RequestPaginationDTO) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.findPagination("Basic "+p_BasicAuth, p_RequestPaginationDTO);
    }

    @Override
    public ResponseDataDTO conductDelete(String p_BasicAuth, Long p_Id) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.delete("Basic "+p_BasicAuth, p_Id);
    }

    @Override
    public ResponseDataDTO insert(String p_BasicAuth, RequestInsertDepartmentDTO p_RequestInsertDepartmentDTO) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.insert("Basic "+p_BasicAuth, p_RequestInsertDepartmentDTO);
    }

    @Override
    public ResponseDepartmentDTO view(String p_BasicAuth, Long p_Id) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.view("Basic " + p_BasicAuth, p_Id);
    }

    @Override
    public ResponseDataDTO update(String p_BasicAuth, ResponseDepartmentDTO p_RequestUpdateDepartmentDTO) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.update("Basic " + p_BasicAuth, p_RequestUpdateDepartmentDTO);
    }
}
