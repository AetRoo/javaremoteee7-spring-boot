package com.sda.practice.springbootpractice.services.implementations;

import com.sda.practice.springbootpractice.models.Users;
import com.sda.practice.springbootpractice.respositories.UsersRepository;
import com.sda.practice.springbootpractice.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserService
 *
 * @author Vinod John
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {
  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void createUser(Users users) {
    users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
    usersRepository.save(users);
  }

  @Override
  public Optional<Users> findUserByUsername(String username) {
    return usersRepository.findByUsername(username);
  }

  @Override
  public Optional<Users> findUserByUsernameAndPassword(String username, String password) {
    return usersRepository.findByUsernameAndPassword(username, password);
  }

  @Override
  public List<Users> getAllUsers() {
    return usersRepository.findAll();
  }
}
