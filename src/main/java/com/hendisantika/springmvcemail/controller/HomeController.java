package com.hendisantika.springmvcemail.controller;

import com.hendisantika.springmvcemail.dto.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/15/17
 * Time: 6:12 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    private static Logger logger = LogManager.getLogger(HomeController.class);

    @GetMapping
    public String showHomePage(Model model) {
//        return "home - Spring MVC Email";
        model.addAttribute("action", new Action());
        return "home";
    }
}
