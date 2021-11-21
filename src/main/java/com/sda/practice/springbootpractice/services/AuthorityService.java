package com.sda.practice.springbootpractice.services;

import com.sda.practice.springbootpractice.models.Authority;

import java.util.List;
import java.util.Optional;

/**
 * Service to handle Authority related operations
 *
 * @author Vinod John
 */
public interface AuthorityService {
  /**
   * To create a new authority
   *
   * @param authority Authority
   */
  void createAuthority(Authority authority);

  /**
   * To find authority by name
   *
   * @param name Authority name
   * @return Optional of Authority
   */
  Optional<Authority> findAuthorityByName(String name);

  /**
   * To get all the authorities
   *
   * @return List of Authority
   */
  List<Authority> getAllAuthorities();
}
