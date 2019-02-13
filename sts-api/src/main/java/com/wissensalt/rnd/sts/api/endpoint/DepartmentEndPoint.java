package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.dao.IDepartmentDAO;
import com.wissensalt.rnd.sts.shared.data.PageRequestBuilder;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.api.mapper.DepartmentMapper;
import com.wissensalt.rnd.sts.shared.data.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentEndPoint {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping("/findAll")
    public List<ResponseDepartmentDTO> findAll() {
        return departmentDAO.findAllDepartment();
    }

    @PostMapping("/findPagination")
    public Page<ResponseDepartmentDTO> findPagination(@RequestBody RequestPaginationDTO p_RequestPagination) {
        PageRequest pageRequest = PageRequestBuilder.build(p_RequestPagination);
        if (pageRequest != null) {
            Page<Department> departments = departmentDAO.findAll(pageRequest);
            return new PageImpl<>(departmentMapper.toDepartmentDTO(departments.getContent()), pageRequest, departments.getTotalElements());
        }else {
            return null;
        }
    }

    @GetMapping("/delete")
    public ResponseDataDTO delete(@RequestParam("id") Long p_Id) {
        departmentDAO.deleteById(p_Id);
        return success();
    }

    @PostMapping("/insert")
    public ResponseDataDTO insert(@RequestBody RequestInsertDepartmentDTO p_RequestInsertDepartmentDTO) {
        departmentDAO.save(departmentMapper.requestToDepartment(p_RequestInsertDepartmentDTO));
        return success();
    }

    @GetMapping("/view")
    public ResponseDepartmentDTO view(@RequestParam("id") Long p_Id) {
        return departmentMapper.toDepartmentDTO(departmentDAO.findById(p_Id).get());
    }

    @PostMapping("/update")
    public ResponseDataDTO update(@RequestBody ResponseDepartmentDTO p_ResponseDepartmentDTO){
        Department department = departmentDAO.findById(p_ResponseDepartmentDTO.getId()).get();
        department.setCode(p_ResponseDepartmentDTO.getCode());
        department.setName(p_ResponseDepartmentDTO.getName());
        department.setRemarks(p_ResponseDepartmentDTO.getRemarks());
        department.setStatus(p_ResponseDepartmentDTO.getStatus());
        departmentDAO.save(department);
        return success();
    }


    @GetMapping("/selectLOV")
    public List<ResponseLOVDTO> selectLOV() {
        return departmentDAO.selectLOV();
    }

    @GetMapping("/count")
    public ResponseDataDTO countNumberOfEntity() {
        return new ResponseDataDTO("200", String.valueOf(departmentDAO.count()));
    }

    private ResponseDataDTO success() {
        return new ResponseDataDTO("200", "Success");
    }
}
