package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.dao.IEmployeeDAO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@Autowired
    private EmployeeMapper employeeMapper;*/

    @GetMapping("/findAll")
    public List<ResponseEmployeeDTO> findAll() {
        return employeeDAO.findAllEmployee();
    }

}
