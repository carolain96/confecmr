package com.sunedu.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    private static final String VIEW_DASHBOARD ="layout/panel"; 
    @GetMapping("app/dashboard")
    public String dashboard(Model model) {
        return VIEW_DASHBOARD;
    }
}
