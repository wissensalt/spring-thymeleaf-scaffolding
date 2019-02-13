package com.wissensalt.rnd.sts.web.controller;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.feign.IScaffoldingClient;
import com.wissensalt.rnd.sts.web.webcomponent.PaginationButton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 * @param <REQUEST_UPDATE>
 */
@Controller
public abstract class AScaffoldingPage<REQUEST, REQUEST_UPDATE> implements IScaffoldingPage {

    protected IScaffoldingClient scaffoldingClient;

    @Override
    public String displayIndex(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        setBasicModelAttributes(p_Model, null);
        p_Model.addAttribute("scaffoldingCreateLink", getScaffoldingCreateLink());
        p_Model.addAttribute("tableHeaders", getTableColumns());

        RequestPaginationDTO paginationDTO = new RequestPaginationDTO();
        paginationDTO.setLimit(5);
        paginationDTO.setOffset(0);
        paginationDTO.setOrder("asc");
        paginationDTO.setSort("id");

        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        p_Model.addAttribute("entityName", getEntityName());
        ResponsePaginationDTO<REQUEST_UPDATE> responsePage = scaffoldingClient.conductFindPagination(basicAuth, paginationDTO);
        p_Model.addAttribute("items", responsePage);
        if (!responsePage.isEmpty()) {
            p_Model.addAttribute("startElement", (responsePage.getNumberOfElements() * responsePage.getNumber() +1));
            p_Model.addAttribute("numberOfElements", (responsePage.getNumberOfElements() * responsePage.getNumber() +responsePage.getNumberOfElements()));
        }else {
            p_Model.addAttribute("startElement", 0);
        }

        p_Model.addAttribute("paginationButtons", PaginationButton.buildStartPagination(responsePage, getPaginationUrl()));
        return getDisplayIndex();
    }

    @Override
    public String displayIndexPaged(Model p_Model, @RequestParam("offset") int p_Offset, @RequestParam("size") int p_Size, HttpServletRequest p_HttpServletRequest) {
        setBasicModelAttributes(p_Model, null);
        p_Model.addAttribute("scaffoldingCreateLink", getScaffoldingCreateLink());
        p_Model.addAttribute("tableHeaders", getTableColumns());

        RequestPaginationDTO paginationDTO = new RequestPaginationDTO();
        paginationDTO.setLimit(p_Size);
        paginationDTO.setOffset(p_Offset);
        paginationDTO.setOrder("asc");
        paginationDTO.setSort("id");

        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        p_Model.addAttribute("entityName", getEntityName());
        ResponsePaginationDTO<REQUEST_UPDATE> responsePage = scaffoldingClient.conductFindPagination(basicAuth, paginationDTO);
        p_Model.addAttribute("items", responsePage);
        if (!responsePage.isEmpty()) {
            p_Model.addAttribute("startElement", (responsePage.getSize() * responsePage.getNumber() +1));
            p_Model.addAttribute("numberOfElements", (responsePage.getSize() * responsePage.getNumber() + responsePage.getNumberOfElements()));
        }else {
            p_Model.addAttribute("startElement", 0);
        }

        p_Model.addAttribute("paginationButtons", PaginationButton.buildChangedPagination(responsePage, getPaginationUrl(), p_Size));
        return getDisplayIndex();
    }

    @Override
    public String displayInsertForm(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        setBasicModelAttributes(p_Model, "NEW");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("actionLink", getInsertLink());
        p_Model.addAttribute("formGroup", getFormGroup(basicAuth));
        p_Model.addAttribute("formButtons", getFormButtons());
        p_Model.addAttribute("requestFormVUI", getSingleObjectRequest());
        return getDisplayInsert();
    }

    @Override
    public String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        setBasicModelAttributes(p_Model, "VIEW");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackLink());
        p_Model.addAttribute("formGroup", getFormGroup(basicAuth));
        p_Model.addAttribute("requestFormVU", getSingleObjectResponse(basicAuth, p_Id));
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
        p_Model.addAttribute("viewMode", false);
        return getDisplayView();
    }

    @PostMapping("/processInsert")
    public abstract RedirectView processInsert(
            HttpServletRequest p_HttpServletRequest,
            @ModelAttribute(value = "requestInsertDTO")
            REQUEST p_Request,
            RedirectAttributes redirectAttributes
    );

    @PostMapping("/processUpdate")
    public abstract RedirectView processUpdate(
            HttpServletRequest p_HttpServletRequest,
            @ModelAttribute(value = "requestFormVU")
            REQUEST_UPDATE p_Request,
            RedirectAttributes redirectAttributes
    );

    public abstract List<String> getTableColumns();
    public abstract String getEntityName();
    public abstract String getDisplayIndex();
    public abstract String getDisplayInsert();
    public abstract String getDisplayView();

    public abstract String getRedirectIndexPage();
    public abstract String getBreadCrumbTitle();
    public abstract String getBreadCrumbSubTitle();
    public abstract String getScaffoldingHeaderTitle();
    public abstract String getScaffoldingCreateLink();
    public abstract String getScaffoldingBackLink();
    public abstract String getHeadTitle();
    public abstract String getPageTitle();
    public abstract String getPageSubtitle();
    public abstract String getInsertLink();
    public abstract String getUpdateLink();
    public abstract String getPaginationUrl();

    public abstract List<Object> getFormGroup(String p_BasicAuth);
    public abstract List<Object> getFormButtons();
    public abstract REQUEST getSingleObjectRequest();
    public abstract REQUEST_UPDATE getSingleObjectResponse(String p_BasicAuth, Long p_Id);

    protected void setBasicModelAttributes(Model p_Model, String p_ScaffoldingHeaderTitle) {
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("pageSubTitle", getPageSubtitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());
        if (p_ScaffoldingHeaderTitle != null) {
            p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle() + "|" + p_ScaffoldingHeaderTitle);
        }else {
            p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle());
        }
    }
}
