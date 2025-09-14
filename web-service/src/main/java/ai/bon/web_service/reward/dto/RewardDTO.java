package ai.bon.web_service.reward.dto;

import ai.bon.web_service.reward.model.Reward;
import ai.bon.web_service.reward.model.RewardStatus;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardDTO {
  private int userId;
  private String rewardId;
  private String description;
  private Date issuedAt;
  private RewardStatus status;
  private String value;

  public static RewardDTO from(Reward reward) {
    return RewardDTO.builder()
        .userId(reward.getKey().getUserId())
        .rewardId(reward.getKey().getId())
        .description(reward.getDescription())
        .issuedAt(reward.getIssuedAt())
        .status(reward.getStatus())
        .value(reward.getValue())
        .build();
  }
}
