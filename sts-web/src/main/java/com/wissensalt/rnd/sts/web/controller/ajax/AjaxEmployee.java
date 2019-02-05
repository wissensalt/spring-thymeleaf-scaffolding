package com.wissensalt.rnd.sts.web.controller.ajax;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.feign.impl.EmployeeClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
@RequestMapping("/ajax/employee")
public class AjaxEmployee {

    @Autowired
    private EmployeeClientImpl employeeClient;

    @GetMapping("/findAll")
    public List<ResponseEmployeeDTO> findAll() {
        return null;
    }

    @GetMapping("/findPagination")
    public ResponsePaginationDTO<ResponseEmployeeDTO> findPagination(HttpServletRequest p_HttpServletRequest) {
        RequestPaginationDTO paginationDTO = new RequestPaginationDTO();
        paginationDTO.setLimit(10);
        paginationDTO.setOffset(0);
        paginationDTO.setOrder("asc");
        paginationDTO.setSort("id");

        return employeeClient.conductFindPagination(SessionUtil.getBasicAuth(p_HttpServletRequest), paginationDTO);
    }

    @GetMapping("/delete")
    public ResponseDataDTO delete(HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        return employeeClient.conductDelete(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Id);
    }

    @PostMapping("/insert")
    public ResponseDataDTO insert(HttpServletRequest p_HttpServletRequest, @RequestBody RequestInsertEmployeeDTO p_RequestInsertEmployeeDTO){
        return employeeClient.insert(SessionUtil.getBasicAuth(p_HttpServletRequest), p_RequestInsertEmployeeDTO);
    }
}
