package com.wissensalt.rnd.sts.web.controller.employee;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.controller.AScaffoldingPage;
import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
import com.wissensalt.rnd.sts.web.feign.impl.EmployeeClientImpl;
import com.wissensalt.rnd.sts.web.webcomponent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
@RequestMapping("/secured/employee")
public class EmployeeController extends AScaffoldingPage<RequestInsertEmployeeDTO, ResponseEmployeeDTO> {

    @Autowired
    private EmployeeClientImpl employeeClient;

    @Autowired
    private DepartmentClientImpl departmentClient;

    @Override
    public RedirectView processInsert(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestInsertDTO") RequestInsertEmployeeDTO p_Request, RedirectAttributes p_RedirectAttributes) {
        employeeClient.insert(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        return super.processInsert(p_HttpServletRequest, p_Request, p_RedirectAttributes);
    }

    @Override
    public RedirectView processUpdate(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestFormVU") ResponseEmployeeDTO p_Request, RedirectAttributes p_RedirectAttributes) {
        employeeClient.update(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        return super.processUpdate(p_HttpServletRequest, p_Request, p_RedirectAttributes);
    }

    @Override
    public List<String> getTableColumns() {
        return new ArrayList<String>(){{
            add("id");
            add("code");
            add("name");
            add("remarks");
            add("salary");
        }};
    }

    @Override
    public String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        super.displayViewForm(p_Model, p_HttpServletRequest, p_Id);

        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(SessionUtil.getBasicAuth(p_HttpServletRequest));
        p_Model.addAttribute("lovDepartment", LovDepartment);
        return getViewURL();
    }

    @Override
    public String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        super.displayUpdateForm(p_Model, p_HttpServletRequest, p_Id);

        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(SessionUtil.getBasicAuth(p_HttpServletRequest));
        p_Model.addAttribute("lovDepartment", LovDepartment);
        return getViewURL();
    }

    @Override
    public String getEntityName() {
        return "employee";
    }

    @Override
    public String getIndexURL() {
        return "/page/employee/employee";
    }

    @Override
    public String getInsertURL() {
        return "/page/employee/employee-insert";
    }

    @Override
    public String getViewURL() {
        return "/page/employee/employee-vu";
    }

    @Override
    public String getRedirectIndexURL() {
        return "/secured/employee";
    }

    @Override
    public String getBreadCrumbTitle() {
        return "Master Data";
    }

    @Override
    public String getBreadCrumbSubTitle() {
        return "Employee";
    }

    @Override
    public String getScaffoldingHeaderTitle() {
        return "EMPLOYEE";
    }

    @Override
    public String getScaffoldingCreateURL() {
        return "/secured/employee/insertForm";
    }

    @Override
    public String getScaffoldingBackURL() {
        return "/secured/employee";
    }

    @Override
    public String getHeadTitle() {
        return "STS - Employee";
    }

    @Override
    public String getPageTitle() {
        return "Master Data";
    }

    @Override
    public String getPageSubtitle() {
        return "Scaffolding";
    }

    @Override
    public String getInsertProcessURL() {
        return "/secured/employee/processInsert";
    }

    @Override
    public String getUpdateProcessURL() {
        return "/secured/employee/processUpdate";
    }

    @Override
    public String getPaginationURL() {
        return "/secured/employee/page";
    }

    @Override
    public ResponseEmployeeDTO getSingleObjectResponse(String p_BasicAuth, Long p_Id) {
        return (ResponseEmployeeDTO) scaffoldingClient.view(p_BasicAuth, p_Id);
    }

    @Override
    public RequestInsertEmployeeDTO getSingleObjectRequest() {
        RequestInsertEmployeeDTO request = new RequestInsertEmployeeDTO();
        request.setStatus(true);
        return request;
    }

    @Override
    public List<Object> getFormInput(String p_BasicAuth) {
        List<Object> result = new ArrayList<>();

        FormGroupInputText groupCode = FormGroupInputText.build("idCode", "code", "Code", "Code", true);
        FormGroupInputText groupName = FormGroupInputText.build("idName", "name", "Name", "Name", true);
        FormGroupInputNumber groupSalary = FormGroupInputNumber.build("idSalary", "salary", "Salary", "Salary");
        FormGroupTextArea groupRemarks = FormGroupTextArea.build("idRemarks", "remarks", "5", "20", "Remarks");
        FormGroupLOV groupLOV = FormGroupLOV.build("idLovDepartment", "departmentId", departmentClient.selectLOV(p_BasicAuth), "Department");
        FormGroupCheckBox groupStatus = FormGroupCheckBox.build("status", "Status");

        result.add(groupCode);
        result.add(groupName);
        result.add(groupLOV);
        result.add(groupSalary);
        result.add(groupRemarks);
        result.add(groupStatus);
        return result;
    }

    @Override
    public List<Object> getFormSearch() {
        return null;
    }

    @Override
    public List<Object> getFormButtons() {
        List<Object> result = new ArrayList<>();
        result.add(ButtonReset.build("Reset"));
        result.add(ButtonSubmit.build("Save"));
        return result;
    }

    @PostConstruct
    @Override
    public void initPage() {
        scaffoldingClient = employeeClient;
    }
}
