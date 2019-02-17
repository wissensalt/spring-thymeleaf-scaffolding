package com.wissensalt.rnd.sts.web.controller.base;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2/17/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ASimplePage implements ISimplePage {
    @Override
    public String display(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        p_Model.addAttribute("pageTitle", getPageTitle());
        p_Model.addAttribute("headTitle", getHeadTitle());
        return getDisplayURL();
    }
}
