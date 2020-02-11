package com.aac.nba.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_AUTH_GROUP")
public class AuthGroupModel {

  @Id
  @Column(name = "user_auth_group_id")
  @GeneratedValue(generator = "user_auth_sequence")
  @SequenceGenerator(name = "user_auth_sequence",
                      allocationSize = 1,
                      initialValue = 1,
                      sequenceName = "user_auth_sequence")
  private long id;

  @Column(name = "username")
  private String username;

  @Column(name = "auth_group")
  private String authGroup;
}
