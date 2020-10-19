package com.example.FirstApp;

import com.example.FirstApp.Services.Implementation.AnswerServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailservice myUserDetailservice;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myUserDetailservice);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()

                /*.antMatchers("/answers").hasRole("USER")
                .antMatchers("/questions").hasRole("ADMIN")*/
                //.antMatchers("/test/{userName}/**").access("@userSecurity.hasUserName(authentication,#userName)") // principals u servisima drugi nacin google it.

                //.antMatchers("/authenticate").permitAll()   // dopusta na toj ruti svima pristup
                //.anyRequest().authenticated()               //  zahtjeva da svaka ruta mora imati autorizaciju (eventualno sa @preauthorize

                .anyRequest().permitAll() // Sve koje nemaju @Preauthorize ili ovdje naglaseno prolaze //ili ovo ili te dvije linije izna da ne budu//

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


        //.antMatchers(HttpMethod.GET, '/somePathForAll').permitAll()
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }



   /* @Component("userSecurity")
    private class UserSecurity{
        public  boolean hasUserName (org.springframework.security.authentication.UsernamePasswordAuthenticationToken authentication, String userName)
        {
            return  authentication.getName().equals(userName);
        }
    }*/
}
