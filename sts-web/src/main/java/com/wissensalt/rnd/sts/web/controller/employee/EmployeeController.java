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
import javax.servlet.http.HttpSession;
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
    public RedirectView processInsert(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestInsertDTO") RequestInsertEmployeeDTO p_Request, RedirectAttributes redirectAttributes) {
        employeeClient.insert(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        redirectAttributes.addFlashAttribute("alert", "Success Insert");
        return new RedirectView(getRedirectIndexPage());
    }

    @Override
    public RedirectView processUpdate(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestFormVU") ResponseEmployeeDTO p_Request, RedirectAttributes redirectAttributes) {
        employeeClient.update(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        redirectAttributes.addFlashAttribute("alert", "Success Update");
        return new RedirectView(getRedirectIndexPage());
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
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        super.setBasicModelAttributes(p_Model, "VIEW");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("formGroup", getFormGroup(basicAuth));
        p_Model.addAttribute("requestFormVU", getSingleObjectResponse(basicAuth, p_Id));

        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(basicAuth);
        p_Model.addAttribute("lovDepartment", LovDepartment);

        p_Model.addAttribute("viewMode", true);
        return getDisplayView();
    }

    @Override
    public String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        setBasicModelAttributes(p_Model, "UPDATE");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("actionLink", getUpdateLink());
        p_Model.addAttribute("formGroup", getFormGroup(basicAuth));
        p_Model.addAttribute("requestFormVU", getSingleObjectResponse(basicAuth, p_Id));
        List<ResponseLOVDTO> LovDepartment = departmentClient.selectLOV(basicAuth);
        p_Model.addAttribute("lovDepartment", LovDepartment);
        p_Model.addAttribute("viewMode", false);
        return getDisplayView();
    }

    @Override
    public String getEntityName() {
        return "employee";
    }

    @Override
    public String getDisplayIndex() {
        return "/page/employee/employee";
    }

    @Override
    public String getDisplayInsert() {
        return "/page/employee/employee-insert";
    }

    @Override
    public String getDisplayView() {
        return "/page/employee/employee-vu";
    }

    @Override
    public String getRedirectIndexPage() {
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
    public String getScaffoldingCreateLink() {
        return "/secured/employee/insertForm";
    }

    @Override
    public String getScaffoldingBackLink() {
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
    public String getInsertLink() {
        return "/secured/employee/processInsert";
    }

    @Override
    public String getUpdateLink() {
        return "/secured/employee/processUpdate";
    }

    @Override
    public String getPaginationUrl() {
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
    public List<Object> getFormGroup(String p_BasicAuth) {
        List<Object> result = new ArrayList<>();

        FormGroupInputText groupCode = new FormGroupInputText();
        groupCode.setHasId(true);
        InputText txtCode = new InputText();
        txtCode.setId("idCode");
        txtCode.setClassName("form-control");
        txtCode.setFieldName("code");
        txtCode.setPlaceHolder("Code");
        txtCode.setRequired(true);

        Label labelCode = new Label();
        labelCode.setText("Code");

        groupCode.setItemLabel(labelCode);
        groupCode.setItemInput(txtCode);

        FormGroupInputText groupName = new FormGroupInputText();
        groupName.setHasId(true);
        InputText txtName = new InputText();
        txtName.setId("idName");
        txtName.setClassName("form-control");
        txtName.setFieldName("name");
        txtName.setPlaceHolder("Name");
        txtName.setRequired(true);

        Label labelName = new Label();
        labelName.setText("Name");

        groupName.setItemLabel(labelName);
        groupName.setItemInput(txtName);

        /*NUMBER SALARY*/
        FormGroupInputNumber groupSalary = new FormGroupInputNumber();
        groupSalary.setHasId(true);
        InputNumber txtSalary = new InputNumber();
        txtSalary.setId("idSalary");
        txtSalary.setClassName("form-control");
        txtSalary.setFieldName("salary");
        txtSalary.setPlaceHolder("Salary");
        txtSalary.setRequired(true);

        Label labelSalary = new Label();
        labelSalary.setText("Salary");

        groupSalary.setItemLabel(labelSalary);
        groupSalary.setItemInput(txtSalary);
        /*NUMBER*/

        FormGroupTextArea groupRemarks = new FormGroupTextArea();
        groupRemarks.setHasId(true);
        InputTextArea txtRemarks  = new InputTextArea();
        txtRemarks.setId("idRemarks");
        txtRemarks.setClassName("form-control");
        txtRemarks.setPlaceHolder(null);
        txtRemarks.setRequired(false);
        txtRemarks.setFieldName("remarks");
        txtRemarks.setRows("5");
        txtRemarks.setCols("20");

        Label labelRemarks = new Label();
        labelRemarks.setText("Remarks");
        groupRemarks.setItemInput(txtRemarks);
        groupRemarks.setItemLabel(labelRemarks);

        /*LOV*/
        FormGroupLOV groupLOV = new FormGroupLOV();
        groupLOV.setHasId(false);
        groupLOV.setId("idLovDepartment");
        groupLOV.setFieldName("departmentId");
        groupLOV.setClassName("form-control");
        List<ResponseLOVDTO> responseLOVDTOs = departmentClient.selectLOV(p_BasicAuth);
        List<LOV> listLovDepartment = new ArrayList<>();
        for (ResponseLOVDTO responseLOVDTO : responseLOVDTOs) {
            LOV lovDepartment  = new LOV();
            lovDepartment.setLovContent(responseLOVDTO);
            listLovDepartment.add(lovDepartment);
        }

        Label labelLovDepartment = new Label();
        labelLovDepartment.setText("Department");

        groupLOV.setItemInput(listLovDepartment);
        groupLOV.setItemLabel(labelLovDepartment);
        /*LOV*/

        FormGroupCheckBox groupStatus = new FormGroupCheckBox();
        groupStatus.setHasId(false);
        InputCheckBox checkboxStatus = new InputCheckBox();
        checkboxStatus.setFieldName("status");
        checkboxStatus.setChecked(true);
        checkboxStatus.setStickyLabel("Status");
        groupStatus.setItemInput(checkboxStatus);

        groupStatus.setItemLabel(null);

        result.add(groupCode);
        result.add(groupName);
        result.add(groupLOV);
        result.add(groupSalary);
        result.add(groupRemarks);
        result.add(groupStatus);
        return result;
    }

    @Override
    public List<Object> getFormButtons() {
        List<Object> result = new ArrayList<>();
        ButtonReset buttonReset = new ButtonReset();
        buttonReset.setText("Reset");
        ButtonSubmit buttonSubmit = new ButtonSubmit();
        buttonSubmit.setText("Save");

        result.add(buttonReset);
        result.add(buttonSubmit);
        return result;
    }

    @PostConstruct
    @Override
    public void initPage() {
        scaffoldingClient = employeeClient;
    }
}
