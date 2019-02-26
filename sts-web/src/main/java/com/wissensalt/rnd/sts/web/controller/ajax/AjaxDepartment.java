package com.wissensalt.rnd.sts.web.controller.ajax;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationCustom;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
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
@RequestMapping("/ajax/department")
public class AjaxDepartment {

    @Autowired
    private DepartmentClientImpl departmentClient;

    @GetMapping("/findAll")
    public List<ResponseDepartmentDTO> findAll() {
        return null;
    }

    @GetMapping("/findPagination")
    public ResponsePaginationDTO<ResponseDepartmentDTO> findPagination(HttpServletRequest p_HttpServletRequest) {
        RequestPaginationDTO paginationDTO = new RequestPaginationDTO();
        paginationDTO.setLimit(10);
        paginationDTO.setOffset(0);
        paginationDTO.setOrder("asc");
        paginationDTO.setSort("id");

        return departmentClient.conductFindPagination(SessionUtil.getBasicAuth(p_HttpServletRequest), paginationDTO);
    }

    @PostMapping("/search")
    public ResponsePaginationDTO<ResponseDepartmentDTO> findPaginationCustom(HttpServletRequest p_HttpServletRequest, @RequestBody RequestPaginationCustom requestPaginationCustom) {
        return departmentClient.findPaginationCustom(SessionUtil.getBasicAuth(p_HttpServletRequest), requestPaginationCustom);
    }

    @GetMapping("/delete")
    public ResponseDataDTO delete(HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        return departmentClient.conductDelete(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Id);
    }

    @PostMapping("/insert")
    public ResponseDataDTO insert(HttpServletRequest p_HttpServletRequest, @RequestBody RequestInsertDepartmentDTO p_RequestInsertDepartmentDTO){
        return departmentClient.insert(SessionUtil.getBasicAuth(p_HttpServletRequest), p_RequestInsertDepartmentDTO);
    }
}
