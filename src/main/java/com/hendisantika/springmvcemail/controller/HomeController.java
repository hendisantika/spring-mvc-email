package com.hendisantika.springmvcemail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/15/17
 * Time: 6:12 AM
 * To change this template use File | Settings | File Templates.
 */

@RestController
@RequestMapping({"/","/home"})
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage() {
        return "home - Spring MVC Email";
    }
}
