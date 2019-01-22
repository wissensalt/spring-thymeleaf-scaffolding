package com.wissensalt.rnd.sts.web.controller.ajax;

import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
