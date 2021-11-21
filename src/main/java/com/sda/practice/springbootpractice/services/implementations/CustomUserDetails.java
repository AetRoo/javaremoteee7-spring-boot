package com.sda.practice.springbootpractice.services.implementations;

import com.sda.practice.springbootpractice.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementation of UserDetails
 *
 * @author Vinod John
 */
@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
  private Users users;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority =  new SimpleGrantedAuthority(users.getAuthority().getName());
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return users.getPassword();
  }

  @Override
  public String getUsername() {
    return users.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
