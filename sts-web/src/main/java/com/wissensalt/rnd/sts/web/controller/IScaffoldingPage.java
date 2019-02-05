package com.wissensalt.rnd.sts.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 1/25/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IScaffoldingPage {

    void initPage();

    @GetMapping(value = "")
    String displayIndex(Model p_Model, HttpServletRequest p_HttpServletRequest);

    @GetMapping("/insertForm")
    String displayInsertForm(Model p_Model, HttpServletRequest p_HttpServletRequest);

    @GetMapping("/viewForm")
    String displayViewForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id);

    @GetMapping("/updateForm")
    String displayUpdateForm(Model p_Model, HttpServletRequest p_HttpServletRequest, @RequestParam("id") Long p_Id);
}
