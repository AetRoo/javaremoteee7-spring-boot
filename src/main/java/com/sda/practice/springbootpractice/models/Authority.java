package com.sda.practice.springbootpractice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Authority model for Security to support User model
 *
 * @author Vinod John
 */
@Entity
@Data
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
}
