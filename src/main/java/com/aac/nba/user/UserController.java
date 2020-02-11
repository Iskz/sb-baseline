package com.aac.nba.user;

import com.aac.nba.user.impl.UserPrincipal;
import com.aac.nba.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  CustomUserDetailsService userService;

  @ResponseBody
  @GetMapping("/api/v1/user")
  @PreAuthorize("#oauth2.hasScope('axiata') and #oauth2.hasScope('read') and hasRole('ROLE_USER')")
  public UserModel getUserDetails() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    UserPrincipal user = (UserPrincipal) userService.loadUserByUsername(username);

    return user.getUser();
  }
}
