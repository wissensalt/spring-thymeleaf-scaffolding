package com.wissensalt.rnd.sts.web.controller.employee;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseEmployeeDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseLOVDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.controller.base.AScaffoldingPage;
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
        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(SessionUtil.getBasicAuth(p_HttpServletRequest));
        p_Model.addAttribute("lovDepartment", LovDepartment);

        return super.displayViewForm(p_Model, p_HttpServletRequest, p_Id);
    }

    @Override
    public String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(SessionUtil.getBasicAuth(p_HttpServletRequest));
        p_Model.addAttribute("lovDepartment", LovDepartment);

        return super.displayUpdateForm(p_Model, p_HttpServletRequest, p_Id);
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
    public List<String > getInsertFormElements(String p_BasicAuth) {
        List<ResponseLOVDTO> lovDepartment = departmentClient.selectLOV(p_BasicAuth);
        List<String> result = new ArrayList<>();

        result.add(FormGroupInputText.build("code", "code", "Code", "Code", true, "", null, false));
        result.add(FormGroupInputText.build("name", "name", "Name", "Name", true, "", null, false));
        result.add(FormGroupInputNumber.build("salary", "Salary", "Salary", "salary", "", "required", false));
        result.add(FormGroupLOV.build("department", "Department", "departmentId", false, lovDepartment, null));
        result.add(FormGroupTextArea.build("remarks", "remarks", "Remarks", "Remarks", "5", "20", "", false));
        result.add(FormGroupCheckBox.build("status", "status", true, false));

        return result;
    }

    @Override
    public List<String> getViewFormElements(String p_BasicAuth, ResponseEmployeeDTO p_ObjectResponse) {
        List<ResponseLOVDTO> lovDepartment = departmentClient.selectLOV(p_BasicAuth);

        List<String> result = new ArrayList<>();
        result.add(FormGroupInputText.build("id", "id", "Id", "Id", true, String.valueOf(p_ObjectResponse.getId()), "readonly", false));
        result.add(FormGroupInputText.build("code", "code", "Code", "Code", true, p_ObjectResponse.getCode(), null, true));
        result.add(FormGroupInputText.build("name", "name", "Name", "Name", true, p_ObjectResponse.getName(), null, true));
        result.add(FormGroupLOV.build("departmentId", "Department", "departmentId", true, lovDepartment, String.valueOf(p_ObjectResponse.getDepartment().getId())));
        result.add(FormGroupTextArea.build("remarks", "remarks", "Remarks", "Remarks", "5", "20", p_ObjectResponse.getRemarks(), true));
        result.add(FormGroupCheckBox.build("status", "status", p_ObjectResponse.getStatus(), true));
        return result;
    }

    @Override
    public List<String> getUpdateFormElements(String p_BasicAuth, ResponseEmployeeDTO p_ObjectResponse) {
        List<ResponseLOVDTO> lovDepartment = departmentClient.selectLOV(p_BasicAuth);

        List<String> result = new ArrayList<>();
        result.add(FormGroupInputText.build("id", "id", "Id", "Id", true, String.valueOf(p_ObjectResponse.getId()), "readonly", false));
        result.add(FormGroupInputText.build("code", "code", "Code", "Code", true, p_ObjectResponse.getCode(), null, false));
        result.add(FormGroupInputText.build("name", "name", "Name", "Name", true, p_ObjectResponse.getName(), null, false));
        result.add(FormGroupLOV.build("departmentId", "Department", "departmentId", false, lovDepartment, String.valueOf(p_ObjectResponse.getDepartment().getId())));
        result.add(FormGroupTextArea.build("remarks", "remarks", "Remarks", "Remarks",  "5", "20", p_ObjectResponse.getRemarks(), false));
        result.add(FormGroupCheckBox.build("status", "status", p_ObjectResponse.getStatus(), false));
        return result;
    }

    @Override
    public List<String> getSearchFormElements(String p_BasicAuth) {
        List<String> result = new ArrayList<>();
        result.add(FormGroupInputText.build("search", "search", "Search", "Input Keyword", false, "", null, false));
        return result;
    }

    @PostConstruct
    @Override
    public void initPage() {
        scaffoldingClient = employeeClient;
    }
}
