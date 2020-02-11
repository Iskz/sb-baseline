package com.aac.nba.user.impl;

import com.aac.nba.user.model.AuthGroupModel;
import com.aac.nba.user.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.*;

public class UserPrincipal implements UserDetails {

  private UserModel user;

  private List<AuthGroupModel> authGroups;

  public UserPrincipal(final UserModel finalUser,
                       final List<AuthGroupModel> authGroupModelList) {
    super();
    this.user = finalUser;
    this.authGroups = authGroupModelList;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (null == authGroups) {
      return Collections.emptyList();
    }

    Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
    authGroups.forEach(group->{
      grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
    });
    return grantedAuthorities;
  }

  @Transient
  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.user.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.user.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.user.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return this.user.isEnabled();
  }

  public UserModel getUser() {
    return this.user;
  }
}
