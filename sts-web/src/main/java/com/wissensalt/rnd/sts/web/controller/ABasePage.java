package com.wissensalt.rnd.sts.web.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2/13/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABasePage implements ISimplePage {

    @Override
    public String display(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        p_Model.addAttribute("headTitle", getHeadTitle());
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("pageSubTitle", getPageSubtitle());
        p_Model.addAttribute("breadCrumbTitle", getBreadCrumbTitle());
        p_Model.addAttribute("breadCrumbSubTitle", getBreadCrumbSubTitle());

        return getDisplayPage();
    }

    public abstract String getHeadTitle();
    public abstract String getPageTitle();
    public abstract String getPageSubtitle();
    public abstract String getBreadCrumbTitle();
    public abstract String getBreadCrumbSubTitle();
    public abstract String getDisplayPage();
}
