package com.wissensalt.rnd.sts.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 1/2/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
}
