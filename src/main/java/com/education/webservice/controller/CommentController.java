package com.education.webservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/comment")
    public String comment(){
        return "comment";
    }
}
