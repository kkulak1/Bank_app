package com.example.BankApplication.appuser;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserResource {
    private final AppUserService appUserService;

    public AppUserResource(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserDetails (@PathVariable("username") String username) {
        AppUser appUser = appUserService.findAppUserByUsername(username);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAllAppUsers () {
        List<AppUser> appUsers = appUserService.findAllAppUsers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> updateAppUser (@RequestBody AppUser appUser) {
        AppUser updateAppUser = appUserService.updateAppUser(appUser);
        return new ResponseEntity<>(updateAppUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppUser (@PathVariable("id") Long id) {
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
