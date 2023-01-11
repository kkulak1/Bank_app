package com.example.BankApplication.App;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class IndexController {
    private final AppUserService appUserService;

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        System.out.println("In Index Page Controller");
        return getIndexPage;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView getLoginPage = new ModelAndView("login");
        System.out.println("In Login Page Controller");
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView getRegisterPage = new ModelAndView("register");
        System.out.println("In Register Page Controller");
        getRegisterPage.addObject("PageTitle", "Register");
        return getRegisterPage;
    }

    @GetMapping("/error")
    public ModelAndView getError(){
        ModelAndView getErrorPage = new ModelAndView("error");
        System.out.println("In Error Page Controller");
        getErrorPage.addObject("PageTitle", "Error");
        return getErrorPage;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(HttpSession session){
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        System.out.println("In Dashboard Page Controller");
        getDashboardPage.addObject("PageTitle", "Dashboard");

//        AppUser appUser = (AppUser)session.getAttribute("user");
//        System.out.println(appUser.getUsername());

//        AppUser appUser = appUserService.getCUrrentUser();
//        System.out.println(appUser.getUsername());

        return getDashboardPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully");
        return "redirect:/login";
    }
}

