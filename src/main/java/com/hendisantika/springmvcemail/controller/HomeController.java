package com.hendisantika.springmvcemail.controller;

import org.springframework.stereotype.Controller;
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
    @GetMapping
    public String showHomePage() {
//        return "home - Spring MVC Email";
        return "home";
    }
}
