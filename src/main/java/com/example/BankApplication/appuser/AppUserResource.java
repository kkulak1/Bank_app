package com.example.BankApplication.appuser;


import com.example.BankApplication.security.models.AuthenticationRequest;
import com.example.BankApplication.security.models.AuthenticationResponse;
import com.example.BankApplication.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/")
//    public String homePage(){
//        return "Welcome!";
//    }

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

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
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

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
