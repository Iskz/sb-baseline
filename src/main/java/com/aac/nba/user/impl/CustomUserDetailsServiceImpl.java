package com.aac.nba.user.impl;

import com.aac.nba.user.CustomUserDetailsService;
import com.aac.nba.user.UserAuthGroupRepository;
import com.aac.nba.user.UserRepository;
import com.aac.nba.user.model.AuthGroupModel;
import com.aac.nba.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  private UserAuthGroupRepository authGroupRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUsername(username);
    if (null == user) {
      throw new UsernameNotFoundException(username);
    }
    List<AuthGroupModel> authGroups = authGroupRepository.findByUsername(username);
    return new UserPrincipal(user, authGroups);
  }

  public UserDetails registerUser(UserModel newUser,
                                  List<AuthGroupModel> newAuthGroupList)
      throws RuntimeException {

    UserModel existingUser = userRepository.findByUsername(newUser.getUsername());
    if (null != existingUser) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username Registered");
    }

    UserModel user = userRepository.save(newUser);
    newAuthGroupList.forEach(authGroup -> authGroup.setId(user.getId()));
    List<AuthGroupModel> userAuthList = authGroupRepository.saveAll(newAuthGroupList);
    return new UserPrincipal(user, userAuthList);
  }

  public UserPrincipal updateUser(UserPrincipal userPrincipal, UserModel newUserModel) {
    return userPrincipal;
  }
}
