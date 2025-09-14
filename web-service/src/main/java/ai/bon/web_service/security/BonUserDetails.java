package ai.bon.web_service.security;

import ai.bon.web_service.user.BonUser;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BonUserDetails implements UserDetails {
  private final BonUser bonUser;

  BonUserDetails(final BonUser bonUser) {
    this.bonUser = bonUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // no roles
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return bonUser.getPasswordHash();
  }

  @Override
  public String getUsername() {
    return String.valueOf(bonUser.getId());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public BonUser getUser() {
    return bonUser;
  }
}
