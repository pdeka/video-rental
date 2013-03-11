package com.example.video.web.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    public String get(HttpSession session) throws Exception {
        session.invalidate();
        return "rediect:login";
    }

}
