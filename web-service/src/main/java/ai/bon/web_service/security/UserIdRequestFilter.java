package ai.bon.web_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class UserIdRequestFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      // Assuming principal is your User entity or contains getId()
      Object principal = authentication.getPrincipal();

      if (principal instanceof BonUserDetails userDetails) {
        int userId = Integer.parseInt(userDetails.getUsername());
        request.setAttribute("userId", userId);
      }
    }

    filterChain.doFilter(request, response);
  }
}
