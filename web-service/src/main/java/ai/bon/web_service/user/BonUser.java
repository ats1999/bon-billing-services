package ai.bon.web_service.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BonUser {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  // email length should not exceed 254 - https://www.rfc-editor.org/errata_search.php?rfc=3696
  @Column(length = 254, unique = true, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String firstName;

  @Column(length = 100, nullable = false)
  private String lastName;

  @Column(length = 200, nullable = false)
  private String passwordHash;
}
