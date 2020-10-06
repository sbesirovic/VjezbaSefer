package com.example.FirstApp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailservice implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // ovdje bi kroy db provjeravat trebao

        if(userName.equals("adminProfile"))
        {
            List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("adminProfile","adminpw",list); // sad prije 8 20 mogao bi za get i post dva razlicita ?
        }
        else if(userName.equals("userProfile"))
        {
            List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
            list.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("userProfile","userpw",list); // sad prije 8 20 mogao bi za get i post dva razlicita ?
        }
        else throw new UsernameNotFoundException("Username "+userName +" doesn't exists");

    }
}
