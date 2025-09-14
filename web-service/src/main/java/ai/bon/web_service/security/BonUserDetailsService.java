package ai.bon.web_service.security;

import ai.bon.web_service.user.BonUser;
import ai.bon.web_service.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BonUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public BonUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    // TODO: redis can be used to cache bonUser details instead of direct DB lookup
    BonUser bonUser =
        userRepository
            .findById(Integer.parseInt(userId))
            .orElseThrow(() -> new UsernameNotFoundException("BonUser not found: " + userId));
    return new BonUserDetails(bonUser);
  }
}
