package com.aac.nba.user;

import com.aac.nba.user.model.AuthGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthGroupRepository extends JpaRepository<AuthGroupModel, Long> {
  public List<AuthGroupModel> findByUsername(final String username);
}
