package com.hendisantika.springmvcemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {
        return "home";
    }
}
