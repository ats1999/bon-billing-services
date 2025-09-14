package ai.bon.web_service.reward.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Reward {
  @EmbeddedId private RewardKey key;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Date issuedAt;

  @Column(nullable = false)
  private RewardStatus status;

  @Column(nullable = false)
  private String value;
}
