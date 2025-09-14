package ai.bon.web_service.user;

import ai.bon.web_service.user.dto.UserDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;

  UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<UserDTO> listUsers(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageRequest = PageRequest.of(page, size);
    Page<BonUser> usersPage = userRepository.findAll(pageRequest);
    Page<UserDTO> userDTOPage = usersPage.map(UserDTO::from);
    return userDTOPage.stream().toList();
  }
}
