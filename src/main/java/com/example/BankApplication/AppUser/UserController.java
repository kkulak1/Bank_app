//package com.example.BankApplication.AppUser;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/v1/user")
//public class UserController {
//
//    private final AppUserService userService;
//
//    @Autowired
//    public UserController(AppUserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<AppUser> getUser(){
//        return userService.getUser();
//    }
//}
