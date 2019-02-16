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
        return super.processInsert(p_HttpServletRequest, p_Request, redirectAttributes);
    }

    @Override
    public RedirectView processUpdate(HttpServletRequest p_HttpServletRequest, @ModelAttribute(value = "requestFormVU") ResponseDepartmentDTO p_Request, RedirectAttributes redirectAttributes) {
        departmentClient.update(SessionUtil.getBasicAuth(p_HttpServletRequest), p_Request);
        return super.processUpdate(p_HttpServletRequest, p_Request, redirectAttributes);
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
    public String getEntityName() {
        return "department";
    }

    @Override
    public String getIndexURL() {
        return "/page/department/department";
    }

    @Override
    public String getInsertURL() {
        return "/page/department/department-insert";
    }

    @Override
    public String getViewURL() {
        return "/page/department/department-vu";
    }

    @Override
    public String getRedirectIndexURL() {
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
    public String getScaffoldingCreateURL() {
        return "/secured/department/insertForm";
    }

    @Override
    public String getScaffoldingBackURL() {
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
    public String getInsertProcessURL() {
        return "/secured/department/processInsert";
    }

    @Override
    public String getUpdateProcessURL() {
        return "/secured/department/processUpdate";
    }

    @Override
    public String getPaginationURL() {
        return "/secured/department/page";
    }

    @Override
    public ResponseDepartmentDTO getSingleObjectResponse(String p_BasicAuth, Long p_Id) {
        return (ResponseDepartmentDTO) scaffoldingClient.view(p_BasicAuth, p_Id);
    }

    @Override
    public RequestInsertDepartmentDTO getSingleObjectRequest() {
        RequestInsertDepartmentDTO request = new RequestInsertDepartmentDTO();
        request.setStatus(true);
        return request;
    }

    @Override
    public List<Object> getFormSearch() {
        List<Object> result = new ArrayList<>();
        result.add(FormGroupInputText.build("searchId", "search", "Input Keyword", "Search", false));
        return result;
    }

    @PostConstruct
    @Override
    public void initPage() {
        scaffoldingClient = departmentClient;
    }
}
