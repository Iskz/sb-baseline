package com.aac.nba.user.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

  @Id
  @GeneratedValue(generator = "USER_SEQUENCE")
  @SequenceGenerator(name = "USER_SEQUENCE",
                      allocationSize = 1,
                      initialValue = 1,
                      sequenceName = "USER_SEQUENCE")
  @Column(name = "USER_ID")
  private Long id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ENABLED")
  private boolean enabled;

  @Column(name = "ACCOUNT_NON_EXPIRED")
  private boolean accountNonExpired;

  @Column(name = "ACCOUNT_NON_LOCKED")
  private boolean accountNonLocked;

  @Column(name = "CREDENTIALS_NON_EXPIRED")
  private boolean credentialsNonExpired;

  @CreationTimestamp
  @Column(name = "CREATED_DATE")
  private Timestamp createdDate;

  @UpdateTimestamp
  @Column(name = "LAST_UPDATED_DATE")
  private Timestamp updatedDate;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "MOBILE_NO")
  private String mobileNo;

  @Column(name = "PASSWORD_RESET")
  private boolean passwordReset;

  public Optional<Timestamp> getCreatedDate() {
    return Optional.ofNullable(createdDate);
  }

  public Optional<Timestamp> getUpdatedDate() {
    return Optional.ofNullable(updatedDate);
  }
}
