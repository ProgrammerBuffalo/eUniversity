package com.website.eUniversity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/")
public class MainController {

    @GetMapping("get")
    public String get()
    {
        return "hello emil";
    }
}
