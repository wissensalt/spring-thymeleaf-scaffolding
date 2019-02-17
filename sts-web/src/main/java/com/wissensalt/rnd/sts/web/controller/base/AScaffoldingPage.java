package com.wissensalt.rnd.sts.web.controller.base;

import com.wissensalt.rnd.sts.shared.data.dto.request.RequestPaginationDTO;
import com.wissensalt.rnd.sts.shared.data.dto.response.ResponsePaginationDTO;
import com.wissensalt.rnd.sts.web.SessionUtil;
import com.wissensalt.rnd.sts.web.feign.IScaffoldingClient;
import com.wissensalt.rnd.sts.web.webcomponent.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        p_Model.addAttribute("scaffoldingCreateLink", getScaffoldingCreateURL());
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

        p_Model.addAttribute("searchFormElements", getSearchFormElements(basicAuth));
        p_Model.addAttribute("paginationButtons", ButtonPagination.buildStartPagination(responsePage, getPaginationURL()));
        return getIndexURL();
    }

    @Override
    public String displayIndexPaged(Model p_Model, @RequestParam("offset") int p_Offset, @RequestParam("size") int p_Size, HttpServletRequest p_HttpServletRequest) {
        setBasicModelAttributes(p_Model, null);
        p_Model.addAttribute("scaffoldingCreateLink", getScaffoldingCreateURL());
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

        p_Model.addAttribute("searchFormElements", getSearchFormElements(basicAuth));
        p_Model.addAttribute("paginationButtons", ButtonPagination.buildChangedPagination(responsePage, getPaginationURL(), p_Size));
        return getIndexURL();
    }

    @Override
    public String displayInsertForm(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        setBasicModelAttributes(p_Model, "NEW");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackURL());
        p_Model.addAttribute("actionLink", getInsertProcessURL());
        p_Model.addAttribute("formElements", getInsertFormElements(basicAuth));
        p_Model.addAttribute("formButtons", getFormButtons());
        p_Model.addAttribute("requestFormVUI", getSingleObjectRequest());
        return getInsertURL();
    }

    @Override
    public String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        HttpSession session = p_HttpServletRequest.getSession(true);
        String basicAuth = (String) session.getAttribute("basicAuth");

        setBasicModelAttributes(p_Model, "VIEW");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackURL());
        REQUEST_UPDATE responseObject = getSingleObjectResponse(basicAuth, p_Id);
        p_Model.addAttribute("requestFormVU", responseObject);
        p_Model.addAttribute("viewMode", true);

        p_Model.addAttribute("formElements", getViewFormElements(basicAuth, responseObject));
        return getViewURL();
    }

    @Override
    public String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id) {
        String basicAuth = SessionUtil.getBasicAuth(p_HttpServletRequest);

        setBasicModelAttributes(p_Model, "UPDATE");
        p_Model.addAttribute("scaffoldingBackLink", getScaffoldingBackURL());
        p_Model.addAttribute("actionLink", getUpdateProcessURL());
        REQUEST_UPDATE responseObject = getSingleObjectResponse(basicAuth, p_Id);
        p_Model.addAttribute("requestFormVU", responseObject);
        p_Model.addAttribute("viewMode", false);

        p_Model.addAttribute("formElements", getUpdateFormElements(basicAuth, responseObject));
        return getViewURL();
    }

    @PostMapping("/processInsert")
    public RedirectView processInsert(
            HttpServletRequest p_HttpServletRequest,
            @ModelAttribute(value = "requestInsertDTO")
            REQUEST p_Request,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("alert", "Success Insert");
        return new RedirectView(getRedirectIndexURL());
    }

    @PostMapping("/processUpdate")
    public RedirectView processUpdate(
            HttpServletRequest p_HttpServletRequest,
            @ModelAttribute(value = "requestFormVU")
            REQUEST_UPDATE p_Request,
            RedirectAttributes redirectAttributes
    ){
        redirectAttributes.addFlashAttribute("alert", "Success Update");
        return new RedirectView(getRedirectIndexURL());
    }

    public abstract List<String> getTableColumns();
    public abstract String getEntityName();

    public abstract String getIndexURL();
    public abstract String getInsertURL();
    public abstract String getViewURL();
    public abstract String getInsertProcessURL();
    public abstract String getUpdateProcessURL();
    public abstract String getScaffoldingCreateURL();
    public abstract String getScaffoldingBackURL();
    public abstract String getPaginationURL();
    public abstract String getRedirectIndexURL();

    public abstract String getBreadCrumbTitle();
    public abstract String getBreadCrumbSubTitle();
    public abstract String getScaffoldingHeaderTitle();

    public abstract String getHeadTitle();
    public abstract String getPageTitle();

    public abstract REQUEST getSingleObjectRequest();
    public abstract REQUEST_UPDATE getSingleObjectResponse(String p_BasicAuth, Long p_Id);

    public abstract List<String> getInsertFormElements(String p_BasicAuth);
    public abstract List<String> getViewFormElements(String p_BasicAuth, REQUEST_UPDATE p_ObjectResponse);
    public abstract List<String> getUpdateFormElements(String p_BasicAuth, REQUEST_UPDATE p_ObjectResponse);
    public abstract List<String> getSearchFormElements(String p_BasicAuth);

    public List<Object> getFormButtons() {
        return getDefaultFormButtons();
    }

    protected void setBasicModelAttributes(Model p_Model, String p_ScaffoldingHeaderTitle) {
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());
        if (p_ScaffoldingHeaderTitle != null) {
            p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle() + "|" + p_ScaffoldingHeaderTitle);
        }else {
            p_Model.addAttribute("scaffoldingHeaderTitle", getScaffoldingHeaderTitle());
        }
    }

    protected List<Object> getDefaultFormButtons() {
        List<Object> result = new ArrayList<>();
        result.add(ButtonReset.build("Reset"));
        result.add(ButtonSubmit.build("Save"));
        return result;
    }

    protected List<Object> getDefaultFormInsert() {
        List<Object> result = new ArrayList<>();

        FormGroupInputText groupCode = FormGroupInputText.build("idCode", "code", "Code", "Code", true);
        FormGroupInputText groupName = FormGroupInputText.build("idName", "name", "Name", "Name", true);
        FormGroupTextArea groupRemarks = FormGroupTextArea.build("idRemarks", "remarks", "5", "20", "Remarks");
        FormGroupCheckBox groupStatus = FormGroupCheckBox.build("status", "Status");

        result.add(groupCode);
        result.add(groupName);
        result.add(groupRemarks);
        result.add(groupStatus);
        return result;
    }
}
