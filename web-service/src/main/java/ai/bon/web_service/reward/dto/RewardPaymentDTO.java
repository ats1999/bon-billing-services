package ai.bon.web_service.reward.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardPaymentDTO {
  private String transactionId;
  private int userId;
  private Date paymentDate;
}
