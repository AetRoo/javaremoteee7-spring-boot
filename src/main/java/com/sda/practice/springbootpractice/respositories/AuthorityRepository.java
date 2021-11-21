package com.sda.practice.springbootpractice.respositories;

import com.sda.practice.springbootpractice.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to handle DB related operations for Authority
 *
 * @author Vinod John
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Optional<Authority> findByName(String name);
}
