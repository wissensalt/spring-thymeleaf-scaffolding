package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
@RequestMapping("/secured/department")
public class DepartmentController {

    @Autowired
    private DepartmentClientImpl departmentClient;

    @GetMapping("")
    public String display(HttpServletRequest httpServletRequest, Model p_Model) {
        List<String> tableHeaders = new ArrayList<>();
        tableHeaders.add("id");
        tableHeaders.add("code");
        tableHeaders.add("name");
        tableHeaders.add("remarks");

        p_Model.addAttribute("departmentTableHeader", tableHeaders);
        p_Model.addAttribute("departments", departmentClient.conductFindAll(String.valueOf(httpServletRequest.getSession().getAttribute("basicAuth"))));
        return "/page/department";
    }
}
