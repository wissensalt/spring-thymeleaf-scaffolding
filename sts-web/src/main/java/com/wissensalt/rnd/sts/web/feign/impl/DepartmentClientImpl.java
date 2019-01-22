package com.wissensalt.rnd.sts.web.feign.impl;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.web.feign.FeignBuilderFactory;
import com.wissensalt.rnd.sts.web.feign.IDepartmentClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class DepartmentClientImpl {

    @Value("${api.base-path}")
    private String apiBasePath;

    public List<ResponseDepartmentDTO> conductFindAll(String p_BasicAuth) {
        IDepartmentClient departmentClient = FeignBuilderFactory.createClient(IDepartmentClient.class, apiBasePath);
        return departmentClient.findAll("Basic "+p_BasicAuth);
    }
}
