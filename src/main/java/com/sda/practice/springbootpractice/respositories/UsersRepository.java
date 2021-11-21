package com.sda.practice.springbootpractice.respositories;

import com.sda.practice.springbootpractice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to handle DB related operations for User
 *
 * @author Vinod John
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
  Optional<Users> findByUsername(String username);

  Optional<Users> findByUsernameAndPassword(String username, String password);
}
