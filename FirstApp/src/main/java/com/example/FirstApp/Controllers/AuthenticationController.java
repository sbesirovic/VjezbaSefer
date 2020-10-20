package com.example.FirstApp.Controllers;


import com.example.FirstApp.Security.JwtUtil;
import com.example.FirstApp.Models.AuthenticationRequest;
import com.example.FirstApp.Models.AuthenticationResponse;
import com.example.FirstApp.Security.MyUserDetailservice;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailservice userDetailservice;

    @Autowired
    JwtUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    @PreAuthorize("permitAll()")
    @ApiOperation(value = "Create JWT", notes = "This method creates a Json Web Token for user sent in parameter")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @ApiParam(name =  "LogInData", value = "Access Data of the user", /*example = "{\n" +
                    "  \"password\": \"ca1234\",\n" +
                    "  \"username\": \"ca1234\"\n" +
                    "}",*/ required = true)         //  % Type && Example don't work with @RequestBody
            @RequestBody
             AuthenticationRequest authenticationRequest) throws  Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException ex)
        {
            throw new Exception("Incorrect username or password",ex);
        }

        final UserDetails userDetails = userDetailservice.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping(value="/test/{userName}")
    @PreAuthorize("#userName == authentication.name")
    public String testAuthoritiesPerProfile(
            @RequestBody AuthenticationRequest authenticationRequest, @PathVariable(value = "userName") String userName) throws  Exception
    {
        return userName;
    }



}
