package com.practice.library.Config.Security;

import com.practice.library.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticator) throws Exception {
        authenticator.userDetailsService(libraryUserService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/", "/login", "/register", "/register/check", "/reader/**", "/css/*", "/img/*","/assets/*").permitAll()
                    .antMatchers("/**").authenticated() //.permitAll() para poder usar sin login
                .and()
                .formLogin()
                    .loginPage("/login") // Route to HTML with login page - @GetMapping("/login")
                        .loginProcessingUrl("/login/check") // th:action url - @PostMapping("/login/check")
                        .usernameParameter("userEmailAddress") // Overriding username and password parameters
                        .passwordParameter("password")
                        .defaultSuccessUrl("/reader", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                        .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .disable();
    }
}
