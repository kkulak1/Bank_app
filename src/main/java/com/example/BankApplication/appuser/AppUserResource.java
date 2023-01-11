package com.example.BankApplication.appuser;


import com.auth0.jwt.HeaderParams;
import com.example.BankApplication.security.models.AuthenticationRequest;
import com.example.BankApplication.security.models.AuthenticationResponse;
import com.example.BankApplication.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@RestController
//@AllArgsConstructor
//@RequestMapping("/user")
public class AppUserResource {
    private final AppUserService appUserService;

//    @Autowired
    private final JwtUtil jwtTokenUtil;
//    @Autowired
    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest, HttpSession session) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = appUserService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

//        HttpSession session = request.getSession();

//        Cookie LoginCookie = new Cookie("username", authenticationRequest.getUsername());
//        LoginCookie.setMaxAge(30*60);
//        response.addCookie(LoginCookie);
//        response.sendRedirect("dashboard.jsp");
//        session.setAttribute("email", authenticationRequest.getUsername());

        session.setAttribute("username", authenticationRequest.getUsername());
//        session.setAttribute("username", authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
