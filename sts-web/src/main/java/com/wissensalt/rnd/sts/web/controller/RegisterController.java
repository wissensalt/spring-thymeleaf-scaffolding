package com.wissensalt.rnd.sts.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 1/10/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping("/display")
    public String display(){
        return "register";
    }
}
