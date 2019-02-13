package com.wissensalt.rnd.sts.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2/13/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(Model p_Model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                p_Model.addAttribute("headTitle", "not found");
                p_Model.addAttribute("pageTitle", "not found");
                p_Model.addAttribute("pageSubTitle", "not found");
                p_Model.addAttribute("breadCrumbTitle", "not found");
                p_Model.addAttribute("breadCrumbSubTitle", "not found");

                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                p_Model.addAttribute("headTitle", "ise");
                p_Model.addAttribute("pageTitle", "ise");
                p_Model.addAttribute("pageSubTitle", "ise");
                p_Model.addAttribute("breadCrumbTitle", "ise");
                p_Model.addAttribute("breadCrumbSubTitle", "ise");
                return "error-500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
