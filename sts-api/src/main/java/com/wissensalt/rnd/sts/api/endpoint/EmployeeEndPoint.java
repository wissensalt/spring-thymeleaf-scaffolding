package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.dao.IEmployeeDAO;
import com.wissensalt.rnd.sts.shared.data.PageRequestBuilder;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDataDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.mapper.EmployeeMapper;
import com.wissensalt.rnd.sts.shared.data.model.Employee;
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
@RequestMapping("/api/employee")
public class EmployeeEndPoint {

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/findAll")
    public List<ResponseEmployeeDTO> findAll() {
        return employeeDAO.findAllEmployee();
    }

    @PostMapping("/findPagination")
    public Page<ResponseEmployeeDTO> findPagination(@RequestBody RequestPaginationDTO p_RequestPagination) {
        PageRequest pageRequest = PageRequestBuilder.build(p_RequestPagination);
        if (pageRequest != null) {
            List<Employee> employees = employeeDAO.findAll(pageRequest).getContent();
            return new PageImpl<>(employeeMapper.toEmployeeDTO(employees));
        }else {
            return null;
        }
    }

    @GetMapping("/delete")
    public ResponseDataDTO delete(@RequestParam("id") Long p_Id) {
        employeeDAO.deleteById(p_Id);
        return success();
    }

    @PostMapping("/insert")
    public ResponseDataDTO insert(@RequestBody RequestInsertEmployeeDTO p_RequestInsertEmployeeDTO) {
        employeeDAO.save(employeeMapper.requestToDepartment(p_RequestInsertEmployeeDTO));
        return success();
    }

    @GetMapping("/view")
    public ResponseEmployeeDTO view(@RequestParam("id") Long p_Id) {
        return employeeMapper.toEmployeeDTO(employeeDAO.findById(p_Id).get());
    }

    @PostMapping("/update")
    public ResponseDataDTO update(@RequestBody ResponseEmployeeDTO p_ResponseEmployeeDTO){
        Employee employee = employeeDAO.findById(p_ResponseEmployeeDTO.getId()).get();
        employee.setCode(p_ResponseEmployeeDTO.getCode());
        employee.setName(p_ResponseEmployeeDTO.getName());
        employee.setRemarks(p_ResponseEmployeeDTO.getRemarks());
        employee.setStatus(p_ResponseEmployeeDTO.getStatus());
        employee.setSalary(p_ResponseEmployeeDTO.getSalary());
        employeeDAO.save(employee);
        return success();
    }

    @GetMapping("/count")
    public ResponseDataDTO countNumberOfEntity() {
        return new ResponseDataDTO("200", String.valueOf(employeeDAO.count()));
    }

    private ResponseDataDTO success() {
        return new ResponseDataDTO("200", "Success");
    }

}
