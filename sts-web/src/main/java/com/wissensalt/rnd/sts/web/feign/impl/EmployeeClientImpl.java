package com.wissensalt.rnd.sts.web.feign.impl;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.feign.FeignBuilderFactory;
import com.wissensalt.rnd.sts.web.feign.IEmployeeClient;
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
public class EmployeeClientImpl implements IScaffoldingClient<RequestInsertEmployeeDTO, ResponseEmployeeDTO> {

    @Value("${api.base-path}")
    private String apiBasePath;

    @Override
    public List<ResponseEmployeeDTO> conductFindAll(String p_BasicAuth) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.findAll("Basic "+p_BasicAuth);
    }

    @Override
    public ResponsePaginationDTO<ResponseEmployeeDTO> conductFindPagination(String p_BasicAuth, RequestPaginationDTO p_RequestPaginationDTO) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.findPagination("Basic "+p_BasicAuth, p_RequestPaginationDTO);
    }

    @Override
    public ResponseDataDTO conductDelete(String p_BasicAuth, Long p_Id) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.delete("Basic "+p_BasicAuth, p_Id);
    }

    @Override
    public ResponseDataDTO insert(String p_BasicAuth, RequestInsertEmployeeDTO p_RequestInsertEmployeeDTO) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.insert("Basic "+p_BasicAuth, p_RequestInsertEmployeeDTO);
    }

    @Override
    public ResponseEmployeeDTO view(String p_BasicAuth, Long p_Id) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.view("Basic " + p_BasicAuth, p_Id);
    }

    @Override
    public ResponseDataDTO update(String p_BasicAuth, ResponseEmployeeDTO p_ResponseEmployeeDTO) {
        System.out.println(p_ResponseEmployeeDTO.toString());
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.update("Basic " + p_BasicAuth, p_ResponseEmployeeDTO);
    }

    @Override
    public ResponseDataDTO count(String p_BasicAuth) {
        IEmployeeClient employeeClient = FeignBuilderFactory.createClient(IEmployeeClient.class, apiBasePath);
        return employeeClient.count("Basic " + p_BasicAuth);
    }

    @Override
    public List<ResponseLOVDTO> selectLOV(String p_BasicAuth) {
        return null;
    }
}
