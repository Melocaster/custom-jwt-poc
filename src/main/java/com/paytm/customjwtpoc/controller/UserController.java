package com.paytm.customjwtpoc.controller;

import com.paytm.customjwtpoc.annotation.Authorized;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @GetMapping("/")
  @Authorized(resource = "USER_ENTITY", permission = "READ")
  public String getUser(@RequestHeader("Authorization") String authToken) {
    return "this is user A";
  }
}
