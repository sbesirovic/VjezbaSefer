package com.example.FirstApp;

import com.example.FirstApp.Exceptions.CustomExceptions.EntityNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailservice userDetailservice;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        try
        {
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
            {
                jwt = authorizationHeader.substring(7); // da bearer izbaci
                username = jwtUtil.extractUsername(jwt);
            }
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = this.userDetailservice.loadUserByUsername(username);

                if(jwtUtil.validateToken(jwt,userDetails))
                {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }
        }
        catch (MalformedJwtException ex)
        {
            System.out.println("WARNING: JWT was malformed");
            // tehnicki bi ja sa dmogo ovdje postavljat status i atribute responsa te uraditi return; da ne idem u filter chain ako zelim
            // da ja svoju poruku greke i tog bacim
        }
        finally
        {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }
}
