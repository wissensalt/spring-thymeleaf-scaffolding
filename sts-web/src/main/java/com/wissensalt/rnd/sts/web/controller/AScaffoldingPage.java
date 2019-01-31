package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestInsertDepartmentDTO;
import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponseDepartmentDTO;
import com.wissensalt.rnd.sts.web.feign.IScaffoldingClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created on 1/25/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <REQUEST>
 */
@Controller
public abstract class AScaffoldingPage<REQUEST> implements IScaffoldingPage {

    protected IScaffoldingClient scaffoldingClient;

    @Override
    public String displayIndex(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("pageSubTitle", getPageSubtitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());
        p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle());
        p_Model.addAttribute("scaffoldingCreateLink", getScaffoldingCreateLink());

        p_Model.addAttribute("tableHeaders", getTableColumns());

        RequestPaginationDTO paginationDTO = new RequestPaginationDTO();
        paginationDTO.setLimit(5);
        paginationDTO.setOffset(0);
        paginationDTO.setOrder("asc");
        paginationDTO.setSort("id");

        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        p_Model.addAttribute("items", scaffoldingClient.conductFindPagination(basicAuth, paginationDTO));
        return getDisplayIndex();
    }

    @Override
    public String displayInsertForm(Model p_Model) {
        RequestInsertDepartmentDTO request = new RequestInsertDepartmentDTO();
        request.setStatus(true);
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("pageSubTitle", getPageSubtitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());
        p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle().concat(" | NEW"));
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("actionLink", getActionLink());
        p_Model.addAttribute("formGroup", getFormGroup());
        p_Model.addAttribute("requestInsertDTO", request);
        p_Model.addAttribute("requestFormVUI", request);
        return getDisplayInsert();
    }

    @Override
    public String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        ResponseDepartmentDTO request = (ResponseDepartmentDTO) scaffoldingClient.view(basicAuth, 91L);
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("pageSubTitle", getPageSubtitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());
        p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle().concat(" | NEW"));
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("actionLink", getActionLink());
        p_Model.addAttribute("formGroup", getFormGroup());
        p_Model.addAttribute("requestInsertDTO", request);
        p_Model.addAttribute("requestFormVUI", request);
        return getDisplayInsert();
    }

    @PostMapping("/processInsert")
    public abstract RedirectView processInsert(
            HttpServletRequest p_HttpServletRequest,
            @ModelAttribute(value = "requestInsertDTO")
            REQUEST p_Request,
            RedirectAttributes redirectAttributes
    );

    public abstract List<String> getTableColumns();
    public abstract String getDisplayIndex();
    public abstract String getDisplayInsert();
    public abstract String getRedirectIndexPage();
    public abstract String getBreadCrumbTitle();
    public abstract String getBreadCrumbSubTitle();
    public abstract String getScaffoldingHeaderTitle();
    public abstract String getScaffoldingCreateLink();
    public abstract String getScaffoldingBackLink();
    public abstract String getHeadTitle();
    public abstract String getPageTitle();
    public abstract String getPageSubtitle();
    public abstract String getActionLink();

    public abstract List<Object> getFormGroup();
}
