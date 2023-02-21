package com.example.BankApplication.appuser;


import com.example.BankApplication.security.models.AuthenticationRequest;
import com.example.BankApplication.security.util.JwtUtil;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.*;
import java.util.List;

@RestController
public class AppUserResource {
    private final AppUserService appUserService;
    private final JwtUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    public static String name;

    public AppUserResource(AppUserService appUserService, JwtUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.appUserService = appUserService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)      // changed from authenticate to login
    public RedirectView createAuthenticationToken(AuthenticationRequest authenticationRequest, HttpSession session) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
            return new RedirectView("/login");
        }

        final UserDetails userDetails = appUserService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        return new RedirectView("/dashboard");
    }

    public String getUsername(){
        return name;
    }
}