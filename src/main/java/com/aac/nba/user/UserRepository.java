package com.aac.nba.user;

import com.aac.nba.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  public UserModel findByUsername(final String username);
}
