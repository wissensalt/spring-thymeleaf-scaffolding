package com.wissensalt.rnd.sts.web.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface ISimplePage {

    String display(Model p_Model, HttpServletRequest p_HttpServletRequest);
}
