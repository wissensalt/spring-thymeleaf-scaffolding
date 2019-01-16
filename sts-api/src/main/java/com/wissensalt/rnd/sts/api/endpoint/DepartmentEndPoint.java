package com.wissensalt.rnd.sts.api.endpoint;

import com.wissensalt.rnd.sts.api.dao.IDepartmentDAO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.mapper.DepartmentMapper;
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
@RequestMapping("/api/department")
public class DepartmentEndPoint {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @GetMapping("/findAll")
    public List<ResponseDepartmentDTO> findAll() {
        return departmentDAO.findAllDepartment();
    }
}
