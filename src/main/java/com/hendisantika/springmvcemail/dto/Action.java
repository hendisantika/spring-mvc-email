package com.hendisantika.springmvcemail.dto;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-mvc-email
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-14
 * Time: 12:07
 */
public class Action {
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Action{" +
                "action='" + action + '\'' +
                '}';
    }
}
