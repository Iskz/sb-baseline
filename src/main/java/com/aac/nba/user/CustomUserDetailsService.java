package com.aac.nba.user;

import com.aac.nba.user.impl.UserPrincipal;
import com.aac.nba.user.model.AuthGroupModel;
import com.aac.nba.user.model.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface CustomUserDetailsService {
  UserDetails registerUser(UserModel newUser,
                           List<AuthGroupModel> newAuthGroupList)
      throws RuntimeException;

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  UserPrincipal updateUser(UserPrincipal userPrincipal, UserModel newUserModel);
}
