package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
import com.wissensalt.rnd.sts.web.feign.impl.EmployeeClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
@RequestMapping("/secured/dashboard")
public class DashboardController implements ISimplePage {

    @Autowired
    private DepartmentClientImpl departmentClient;

    @Autowired
    private EmployeeClientImpl employeeClient;

    @GetMapping("")
    @Override
    public String display(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        String basicAuth = SessionUtil.getBasicAuth(p_HttpServletRequest);
        p_Model.addAttribute("nDepartment", departmentClient.count(basicAuth).getResponseMsg());
        p_Model.addAttribute("nEmployee", employeeClient.count(basicAuth).getResponseMsg());
        return "/page/dashboard";
    }
}
