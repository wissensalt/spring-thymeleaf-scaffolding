package com.wissensalt.rnd.sts.web.controller.impl;

import com.wissensalt.rnd.sts.web.controller.base.ASimplePage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 1/21/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class LoginController extends ASimplePage {

    @GetMapping("/login")
    @Override
    public String display(Model p_Model, HttpServletRequest p_HttpServletRequest) {
        return super.display(p_Model, p_HttpServletRequest);
    }

    @Override
    public String getDisplayURL() {
        return "/page/login";
    }

    @Override
    public String getPageTitle() {
        return "Login";
    }

    @Override
    public String getHeadTitle() {
        return "STS - LOGIN";
    }
}
