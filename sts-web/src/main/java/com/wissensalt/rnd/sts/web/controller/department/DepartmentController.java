package com.wissensalt.rnd.sts.web.controller.department;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.controller.AScaffoldingPage;
import com.wissensalt.rnd.sts.web.feign.impl.DepartmentClientImpl;
import com.wissensalt.rnd.sts.web.webcomponent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/secured/department")
public class DepartmentController extends AScaffoldingPage<RequestInsertDepartmentDTO, ResponseDepartmentDTO> {

    @Autowired
    private DepartmentClientImpl departmentClient;

    @Override
    public RedirectView processInsert(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestInsertDTO") RequestInsertDepartmentDTO p_Request, RedirectAttributes redirectAttributes) {
        departmentClient.insert(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        redirectAttributes.addFlashAttribute("alert", "Success Insert");
        return new RedirectView(getRedirectIndexPage());
    }

    @Override
    public RedirectView processUpdate(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestFormVU") ResponseDepartmentDTO p_Request, RedirectAttributes redirectAttributes) {
        departmentClient.update(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
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
        }};
    }

    @Override
    public String getDisplayIndex() {
        return "/page/department/department";
    }

    @Override
    public String getDisplayInsert() {
        return "/page/department/department-insert";
    }

    @Override
    public String getDisplayView() {
        return "/page/department/department-vu";
    }

    @Override
    public String getRedirectIndexPage() {
        return "/secured/department";
    }

    @Override
    public String getBreadCrumbTitle() {
        return "Master Data";
    }

    @Override
    public String getBreadCrumbSubTitle() {
        return "Scaffolding";
    }

    @Override
    public String getScaffoldingHeaderTitle() {
        return "DEPARTMENT";
    }

    @Override
    public String getScaffoldingCreateLink() {
        return "/secured/department/insertForm";
    }

    @Override
    public String getScaffoldingBackLink() {
        return "/secured/department/";
    }

    @Override
    public String getHeadTitle() {
        return "STS - Department";
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
        return "/secured/department/processInsert";
    }

    @Override
    public String getUpdateLink() {
        return "/secured/department/processUpdate";
    }

    @Override
    public List<Object> getFormGroup() {
        List<Object> result = new ArrayList<>();

        FormGroupInputText groupCode = new FormGroupInputText();
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

        FormGroupTextArea groupRemarks = new FormGroupTextArea();
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

        FormGroupCheckBox groupStatus = new FormGroupCheckBox();
        InputCheckBox checkboxStatus = new InputCheckBox();
        checkboxStatus.setFieldName("status");
        checkboxStatus.setChecked(true);
        groupStatus.setItemInput(checkboxStatus);

        groupStatus.setItemLabel(null);

        result.add(groupCode);
        result.add(groupName);
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
        scaffoldingClient = departmentClient;
    }
}
