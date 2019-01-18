package com.wissensalt.rnd.sts.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 1/2/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class IndexController {

    @Value("${web.head.title}")
    private String webTitle;

    @Value("${web.head.subtitle}")
    private String webSubtitle;

    @Value("${web.footer.version}")
    private String version;

    @Value("${web.footer.year-range}")
    private String yearRange;

    @Value("${web.footer.link}")
    private String link;

    @Value("${web.footer.link-text}")
    private String linkText;

    @GetMapping("/")
    public String showIndex(Model p_Model) {
        p_Model.addAttribute("webTitle", webTitle);
        p_Model.addAttribute("webSubtitle", webSubtitle);
        p_Model.addAttribute("version", version);
        p_Model.addAttribute("yearRange", yearRange);
        p_Model.addAttribute("link", link);
        p_Model.addAttribute("linkText", linkText);
        return "index";
    }
}
