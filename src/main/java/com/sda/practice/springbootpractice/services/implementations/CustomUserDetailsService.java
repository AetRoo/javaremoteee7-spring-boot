package com.sda.practice.springbootpractice.services.implementations;

import com.sda.practice.springbootpractice.models.Users;
import com.sda.practice.springbootpractice.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Customized implementation of UserDetailsService
 *
 * @author Vinod John
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UsersService usersService;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<Users> optionalUser = usersService.findUserByUsername(s);

    if(!optionalUser.isPresent()){
      throw new UsernameNotFoundException("User not found with username: " + s);
    }

    return new CustomUserDetails(optionalUser.get());
  }
}
