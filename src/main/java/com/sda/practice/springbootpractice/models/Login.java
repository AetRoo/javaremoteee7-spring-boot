package com.sda.practice.springbootpractice.models;

import lombok.Data;

/**
 * Login model for security
 *
 * @author Vinod John
 */
@Data
public class Login {
  private String username;
  private String password;
}
