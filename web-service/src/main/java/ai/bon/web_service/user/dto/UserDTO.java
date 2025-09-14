package ai.bon.web_service.user.dto;

import ai.bon.web_service.user.BonUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
  private int id;
  private String firstName;
  private String lastName;

  public static UserDTO from(BonUser bonUser) {
    return UserDTO.builder()
        .id(bonUser.getId())
        .firstName(bonUser.getFirstName())
        .lastName(bonUser.getLastName())
        .build();
  }
}
