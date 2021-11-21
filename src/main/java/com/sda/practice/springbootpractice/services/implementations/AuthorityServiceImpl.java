package com.sda.practice.springbootpractice.services.implementations;

import com.sda.practice.springbootpractice.models.Authority;
import com.sda.practice.springbootpractice.respositories.AuthorityRepository;
import com.sda.practice.springbootpractice.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service to handle Authority related operations
 *
 * @author Vinod John
 */
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public void createAuthority(Authority authority) {
    if(!findAuthorityByName(authority.getName()).isPresent()) {
      authorityRepository.save(authority);
    }
  }

  @Override
  public Optional<Authority> findAuthorityByName(String name) {
    return authorityRepository.findByName(name);
  }

  @Override
  public List<Authority> getAllAuthorities() {
    return authorityRepository.findAll();
  }
}
