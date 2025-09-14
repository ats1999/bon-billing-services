package ai.bon.web_service.reward.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class RewardKey {
  private int userId;
  private String id;
}
