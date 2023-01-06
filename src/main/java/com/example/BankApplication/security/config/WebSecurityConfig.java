package com.example.BankApplication.security.config;

import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.security.filter.JwtRequestFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()   //to send post request without being rejected
//                .authorizeRequests()
//                .antMatchers("/api/v*/registration/**")//allow everything after registration
//                .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin();

////        http.csrf().disable()
////                .authorizeRequests().antMatchers("/api/v*/registration").permitAll().
////                anyRequest().authenticated();
//
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/api/v*/registration").permitAll().
//                anyRequest().authenticated();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register/**", "/register", "/", "/dashboard").permitAll()
//                .antMatchers("/api/v*/registration/**").permitAll()
//                .antMatchers("/register").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/home-page").permitAll()
//                .antMatchers("/register/**").permitAll()
//                .anyRequest().authenticated()
                .and()
//                .exceptionHandling().and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/dashboard")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // make sure this filter is called before UsernamePasswordAuthen...



//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().anyRequest().permitAll();
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        http.formLogin()
//                .defaultSuccessUrl("/", true);

//                .defaultSuccessUrl("/", true);
//                .and()
//                .oauth2ResourceServer()
//                .jwt();

//        http.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
//                .antMatchers("/anonymous*")
//                .anonymous()
//                .antMatchers("/login*")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login-or-register")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html", true)
//                .failureUrl("/login.html?error=true")
////                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID");
////                .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(appUserService);       //added that       to second configuration
        auth.authenticationProvider(daoAuthenticationProvider());
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{
//        auth.userDetailsService(appUserService);
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}